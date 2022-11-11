package com.bebas.org.common.security.filter;

import cn.hutool.core.collection.CollUtil;
import com.bebas.org.common.config.security.GlobalRouteConfig;
import com.bebas.org.common.constants.SecurityConstant;
import com.bebas.org.common.security.vo.LoginUser;
import com.org.bebasWh.exception.UserException;
import com.org.bebasWh.utils.ServletUtils;
import com.org.bebasWh.utils.StringUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Wuhao
 * @date 2022/9/5 20:47
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws UserException {
        if (CollUtil.isEmpty(configAttributes)) {
            return;
        }
        FilterInvocation filterInvocation = (FilterInvocation) object;
        // 白名单过滤请求
        String requestURI = filterInvocation.getRequest().getRequestURI();
        for (String passRoutePath : GlobalRouteConfig.getPassRoutePath()) {
            if (passRoutePath.equals(requestURI)) return;
        }
        if (authentication.getPrincipal() instanceof String) {
            if (authentication.getPrincipal().equals("anonymousUser")) {
                String msg = StringUtils.format("请求访问：{}，认证失败，无法访问系统资源", requestURI);
                throw new AccessDeniedException(msg);
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if (Objects.isNull(loginUser)) {
            ServletUtils.redirectErrorRequest(filterInvocation.getRequest(), filterInvocation.getResponse(), "无法获取用户信息");
            throw new AccessDeniedException("无法获取用户信息");
        }
        for (ConfigAttribute configAttribute : configAttributes) {
            //将访问所需资源或用户拥有资源进行比对
            String needAuthority = configAttribute.getAttribute();
            for (String grantedAuthority : loginUser.getPermissions()) {
                // *:*:* 表示所有授权接口都有权限
                if (grantedAuthority.equals(SecurityConstant.PERMISSION_TAG)) {
                    return;
                }
                if (needAuthority.trim().equals(grantedAuthority)) {
                    return;
                }
            }
        }
        ServletUtils.redirectErrorRequest(filterInvocation.getRequest(), filterInvocation.getResponse(), "无权限访问");
        throw new AccessDeniedException("无权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
