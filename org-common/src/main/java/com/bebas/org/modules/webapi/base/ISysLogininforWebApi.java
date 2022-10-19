package com.bebas.org.modules.webapi.base;

/**
 * @author Wuhao
 * @date 2022/9/7 22:34
 */
public interface ISysLogininforWebApi {

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "Success";
    /**
     * 注销
     */
    String LOGOUT = "Logout";
    /**
     * 注册
     */
    String REGISTER = "Register";
    /**
     * 登录失败
     */
    String LOGIN_FAIL = "Error";

    void insertLoginLog(final String username, final String status, final String message);

}
