package com.bebas.org.modules.model.base.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 注册对象实体
 *
 * @author WuHao
 * @date 2022/5/29 16:11
 */
@Data
public class RegisterBodyVo {

    @NotNull(message = "登陆帐号不能为空！")
    @Length(max = 20, message = "登录账号长度不能超过20位")
    private String userName;

    @NotNull(message = "昵称不能为空！")
    @Length(max = 22, message = "昵称超长，请缩小昵称长度")
    private String nickName;

    @NotNull(message = "密码不能为空！")
    @Length(min = 6, max = 22, message = "密码长度需要在6~22位数之间")
    private String password;

    private Long roleId;

}
