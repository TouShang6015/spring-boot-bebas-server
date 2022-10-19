package com.bebas.module.base.core.dataPermission.range;

import cn.hutool.core.collection.CollUtil;
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
 * 本部门数据权限 策略实现
 *
 * @author Wuhao
 * @date 2022/8/28 22:47
 */
@Service
public class PermissionRangeDeptMy implements PerRangeStrategy {

    @Resource
    private ISysRoleDeptService sysRoleDeptService;

    @Override
    public String getRangeSign() {
        return PermissionRangeEnums.DEPT_MY.name();
    }

    @Override
    public void scopeFilter(JoinPoint joinPoint, LoginUser user, SysRoleModel role) {
        List<Long> deptIds = CollUtil.newArrayList(user.getDeptId());

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof SysRoleModel) {
                List<SysRoleDeptModel> roleDeptList = sysRoleDeptService.lambdaQuery().in(SysRoleDeptModel::getDeptId, deptIds).list();
                String mainIds = roleDeptList.stream().map(SysRoleDeptModel::getRoleId).distinct().map(String::valueOf).collect(Collectors.joining(StringPool.COMMA));
                ((SysRoleModel) arg).queryParamIn(Constants.MAIN_ID, mainIds);
            }
            if (arg instanceof SysUserModel) {
                String mainIds = deptIds.stream().map(String::valueOf).collect(Collectors.joining(StringPool.COMMA));
                ((SysUserModel) arg).queryParamIn("deptId", mainIds);
            }
            if (arg instanceof SysDeptModel) {
                String mainIds = deptIds.stream().map(String::valueOf).collect(Collectors.joining(StringPool.COMMA));
                ((SysDeptModel) arg).queryParamIn(Constants.MAIN_ID, mainIds);
            }
        }
    }

}
