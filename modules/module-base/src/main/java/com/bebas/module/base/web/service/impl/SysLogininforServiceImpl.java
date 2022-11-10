package com.bebas.module.base.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bebas.module.base.mapper.SysLogininforMapper;
import com.bebas.module.base.web.service.ISysLogininforService;
import com.bebas.org.common.constants.ChannelConstant;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.framework.asyncMessage.MessageService;
import com.bebas.org.framework.asyncMessage.annotation.MessageListener;
import com.bebas.org.modules.model.base.model.SysLogininforModel;
import com.org.bebasWh.core.json.JSONObjectBuilder;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.utils.ServletUtils;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.ip.AddressUtils;
import com.org.bebasWh.utils.ip.IpUtils;
import com.org.bebasWh.utils.logs.LogUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 系统访问记录 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 08:51:34
 */
@Service
public class SysLogininforServiceImpl extends ServiceImpl<SysLogininforMapper, SysLogininforModel> implements ISysLogininforService {

    @Resource
    private MessageService messageService;

    @Override
    public void insertLoginLog(String username, String status, String message) {
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        JSONObject jsonObject = JSONObjectBuilder.builder()
                .put("username", username)
                .put("status", status)
                .put("message", message)
                .put("ip", ip)
                .put("os", userAgent.getOperatingSystem().getName())
                .put("browser", userAgent.getBrowser())
                .build();
        messageService.send(ChannelConstant.Log.LOGIN, jsonObject.toJSONString());
    }

    @MessageListener(ChannelConstant.Log.LOGIN)
    public void loginLogInsert(byte[] data) {
        JSONObject jsonObject = JSONObject.parseObject(new String(data, Charset.defaultCharset()));
        String username = jsonObject.get("username").toString();
        String status = jsonObject.get("status").toString();
        String message = jsonObject.get("message").toString();
        String ip = jsonObject.get("ip").toString();
        String os = jsonObject.get("os").toString();
        String browser = jsonObject.get("browser").toString();

        String address = AddressUtils.getRealAddressByIP(ip);
        StringBuilder s = new StringBuilder();
        s.append(LogUtil.getBlock(ip));
        s.append(address);
        s.append(LogUtil.getBlock(username));
        s.append(LogUtil.getBlock(status));
        s.append(LogUtil.getBlock(message));
        // 打印信息到日志
        log.info(s.toString());
        // 封装对象
        SysLogininforModel logininfor = new SysLogininforModel();
        logininfor.setUserName(username);
        logininfor.setIpaddr(ip);
        logininfor.setLoginLocation(address);
        logininfor.setMsg(message);
        logininfor.setLoginTime(new Date());
        logininfor.setBrowser(browser);
        logininfor.setOs(os);
        // 日志状态
        if (StringUtils.equalsAny(status, LOGIN_SUCCESS, LOGOUT, REGISTER)) {
            logininfor.setStatus(Integer.valueOf(Constants.Status.NORMAL));
        } else if (LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Integer.valueOf(Constants.Status.NO_NORMAL));
        }
        super.save(logininfor);
    }

    @Override
    public void clean() {
        baseMapper.clean();
    }
}
