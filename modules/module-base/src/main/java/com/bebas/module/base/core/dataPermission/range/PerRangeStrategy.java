package com.bebas.module.base.core.dataPermission.range;

import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import org.aspectj.lang.JoinPoint;

/**
 * 数据权限类型范围策略器
 *
 * @author Wuhao
 * @date 2022/8/28 22:43
 */
public interface PerRangeStrategy {

    String getRangeSign();

    void scopeFilter(JoinPoint joinPoint, LoginUser user, SysRoleModel role);

}
