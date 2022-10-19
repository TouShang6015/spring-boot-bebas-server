package com.bebas.module.base.core.dataPermission.range;

import com.bebas.module.base.core.dataPermission.enums.PermissionRangeEnums;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

/**
 * 所有权限 策略实现
 *
 * @author Wuhao
 * @date 2022/8/28 22:47
 */
@Service
public class PermissionRangeAll implements PerRangeStrategy {

    @Override
    public String getRangeSign() {
        return PermissionRangeEnums.ALL.name();
    }

    @Override
    public void scopeFilter(JoinPoint joinPoint, LoginUser user, SysRoleModel role) {
    }

}
