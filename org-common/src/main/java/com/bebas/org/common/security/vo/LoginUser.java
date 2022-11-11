package com.bebas.org.common.security.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.org.bebasWh.core.security.model.BaseSecurityUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author WuHao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginUser extends BaseSecurityUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 角色列表
     */
    private Set<Long> roleIds;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUserModel userModel;

    public LoginUser() {
    }

    public LoginUser(SysUserModel userModel, Set<String> permissions) {
        this.userModel = userModel;
        this.permissions = permissions;
        super.setUserId(userModel.getId());
        super.setUserName(userModel.getUserName());
        super.setPassword(userModel.getPassword());
    }

    public LoginUser(Long userId, Long deptId, SysUserModel userModel, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.userModel = userModel;
        this.permissions = permissions;
        super.setUserId(userModel.getId());
        super.setUserName(userModel.getUserName());
        super.setPassword(userModel.getPassword());
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
