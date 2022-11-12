package com.bebas.module.base.core.auth;

import com.bebas.module.base.web.service.ISysLogininforService;
import com.bebas.module.base.web.service.ISysUserTokenService;
import com.bebas.org.common.config.global.security.RsaConfig;
import com.bebas.org.common.security.service.TokenService;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.utils.RsaUtils;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import com.bebas.org.modules.model.base.vo.LoginPcRequest;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.exception.UserException;
import com.org.bebasWh.utils.DateUtils;
import com.org.bebasWh.utils.ServletUtils;
import com.org.bebasWh.utils.ip.IpUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bebas.org.common.constants.MessageCode.User.*;

/**
 * @author WuHao
 * @date 2022/5/31 19:08
 */
public abstract class PcLoginWorker extends LoginCommonAbstract<LoginPcRequest, LoginUser> {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private ISysUserTokenService sysUserTokenService;
    @Resource
    private ISysLogininforService sysLogininforService;

    /**
     * 是否开启验证码
     */
    protected abstract boolean openAuthCode();

    /**
     * 获取最大登陆人数
     *
     * @return
     */
    @Override
    protected Integer getMaxLogin() {
        return this.mainVO.getMaxUserLogin();
    }

    /**
     * 登陆操作，获取实体
     *
     * @param loginPcRequest
     * @return
     */
    @Override
    public LoginUser doLogin(LoginPcRequest loginPcRequest) {
        String password = null;
        try {
            // 解密前端加密后的密码
            password = RsaUtils.decryptByPrivateKey(RsaConfig.getPrivateKey(), loginPcRequest.getPassword());
        } catch (Exception e) {
            throw new UserException(MessageUtils.message(LOGIN_FAIL_PARAM_NO_MATCH));
        }
        // 用户验证
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginPcRequest.getUserName(), password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                sysLogininforService.insertLoginLog(loginPcRequest.getUserName(), sysLogininforService.LOGIN_FAIL, MessageUtils.message(USER_NOT_FOUND_PASSWORD_NOT_MATCH));
                throw new UserException(MessageUtils.message(USER_NOT_FOUND_PASSWORD_NOT_MATCH));
            } else {
                sysLogininforService.insertLoginLog(loginPcRequest.getUserName(), sysLogininforService.LOGIN_FAIL, e.getMessage());
                throw new UserException(e.getMessage());
            }
        }
        sysLogininforService.insertLoginLog(loginPcRequest.getUserName(), sysLogininforService.LOGIN_SUCCESS, MessageUtils.message(LOGIN_SUCCESS));
        LoginUser principal = (LoginUser) authentication.getPrincipal();
        principal.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        return (LoginUser) authentication.getPrincipal();
    }

    /**
     * 获取token
     *
     * @param loginUser
     * @return
     */
    @Override
    public String getToken(LoginUser loginUser) {
        String token = tokenService.createToken(loginUser);
        // 将token保存在数据库中，用于限制最大登录人数
        saveTokenInDataBase(loginUser);
        return token;
    }

    /**
     * 存储token，限制最大登陆数
     *
     * @param loginUser
     * @return
     */
    private void saveTokenInDataBase(LoginUser loginUser) {
        SysUserTokenModel sysUserToken = SysUserTokenModel.builder()
                .userId(loginUser.getUserModel().getId())
                .token(loginUser.getToken())
                .lastTime(DateUtils.nowDateFormat())
                .build();
        sysUserTokenService.save(sysUserToken);
        // 删除redis存放的token，重新插入
        List<SysUserTokenModel> modelList = sysUserTokenService.lambdaQuery().eq(SysUserTokenModel::getUserId, sysUserToken.getUserId()).list();
        Optional.ofNullable(modelList).ifPresent(list -> {
            int maxLogin = this.getMaxLogin();
            int count = list.size();
            if (count > maxLogin) {
                int num = count - maxLogin;
                // 正序排列
                List<SysUserTokenModel> descList = modelList.parallelStream().sorted(Comparator.comparing(SysUserTokenModel::getLastTime)).collect(Collectors.toList());
                for (int i = 0; i < num; i++) {
                    tokenService.delLoginUser(descList.get(i).getToken());
                    sysUserTokenService.removeById(descList.get(i).getId());
                }
            }
        });
    }


}
