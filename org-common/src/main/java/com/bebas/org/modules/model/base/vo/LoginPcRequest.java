package com.bebas.org.modules.model.base.vo;

import lombok.Data;

/**
 * 客户端登陆实体
 * @author WuHao
 * @date 2022/5/31 14:19
 */
@Data
public class LoginPcRequest {

    /**
     * 登录账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * 唯一标识
     */
    private String uuid;
    /**
     * 记住我
     */
    private Boolean rememberMe;

}
