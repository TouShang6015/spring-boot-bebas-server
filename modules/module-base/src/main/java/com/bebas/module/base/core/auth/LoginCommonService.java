package com.bebas.module.base.core.auth;

/**
 * 通用登陆接口
 * @author WuHao
 * @date 2022/5/31 19:01
 */
public interface LoginCommonService<LoginRequest,LoginResponse> {

    /**
     * 登陆操作，获取实体
     * @param request
     * @return
     */
    LoginResponse doLogin(LoginRequest request);

    /**
     * 获取token
     * @param loginUser
     * @return
     */
    String getToken(LoginResponse loginUser);

}
