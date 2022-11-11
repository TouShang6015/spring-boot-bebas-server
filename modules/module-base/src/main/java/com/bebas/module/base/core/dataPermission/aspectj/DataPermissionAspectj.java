package com.bebas.module.base.core.dataPermission.aspectj;

import com.bebas.module.base.core.dataPermission.annotation.PermissionData;
import com.bebas.module.base.core.dataPermission.context.PerRangeContext;
import com.bebas.module.base.core.dataPermission.range.PerRangeStrategy;
import com.bebas.module.base.web.service.ISysRoleService;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * 数据权限过滤切面
 *
 * @author wuhao
 * @date 2022/8/29 17:51
 */
@Aspect
@Component
public class DataPermissionAspectj {

    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private PerRangeContext perRangeContext;

    @Before("@annotation(dataAnnotation)")
    public void doBefore(JoinPoint point, PermissionData dataAnnotation) throws Throwable {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (Objects.nonNull(loginUser)) {
            // 如果是超级管理员，则不过滤数据
            if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
                Optional.ofNullable(sysRoleService.lambdaQuery().in(SysRoleModel::getId, loginUser.getRoleIds()).list()).ifPresent(roleList -> {
                    roleList.forEach(role -> {
                        PerRangeStrategy perRangeService = perRangeContext.getPerRangeService(role.getDataScope());
                        if (Objects.nonNull(perRangeService)) {
                            perRangeService.scopeFilter(point, loginUser, role);
                        }
                    });
                });
            }
        }
    }

}
