package com.bebas.org.common.security.handle;

import com.alibaba.fastjson2.JSON;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.security.service.TokenService;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.modules.webapi.base.ISysLogininforWebApi;
import com.org.bebasWh.utils.ServletUtils;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义退出处理类 返回成功
 *
 * @author wuhao
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;
    @Resource
    private ISysLogininforWebApi sysLogininforWebApi;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            sysLogininforWebApi.insertLoginLog(userName, sysLogininforWebApi.LOGOUT, MessageUtils.message(MessageCode.System.LOGOUT_SUCCESS));
        }
        ServletUtils.renderString(response, JSON.toJSONString(Result.success("退出成功")));
    }
}
