package com.bebas.module.base.core.dataPermission.range;

import com.bebas.module.base.core.dataPermission.enums.PermissionRangeEnums;
import com.bebas.module.base.web.service.ISysRoleDeptService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义数据权限 策略实现
 *
 * @author Wuhao
 * @date 2022/8/28 22:47
 */
@Service
public class PermissionRangeCustom implements PerRangeStrategy {

    @Resource
    private ISysRoleDeptService sysRoleDeptService;

    @Override
    public String getRangeSign() {
        return PermissionRangeEnums.CUSTOM.name();
    }

    @Override
    public void scopeFilter(JoinPoint joinPoint, LoginUser user, SysRoleModel role) {

        List<SysRoleDeptModel> roleDeptList = sysRoleDeptService.lambdaQuery().in(SysRoleDeptModel::getRoleId, role.getId()).list();
        String deptIds = roleDeptList.stream().map(SysRoleDeptModel::getDeptId).distinct().map(String::valueOf).collect(Collectors.joining(StringPool.COMMA));

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SysRoleModel) {
                String mainIds = role.getId().toString();
                ((SysRoleModel) arg).queryParamIn(Constants.MAIN_ID, mainIds);
            } else if (arg instanceof SysUserModel) {
                ((SysUserModel) arg).queryParamIn("deptId", deptIds);
            } else if (arg instanceof SysDeptModel) {
                ((SysDeptModel) arg).queryParamIn(Constants.MAIN_ID, deptIds);
            }
        }
    }

}
