package com.bebas.module.base.core.dataPermission.range;

import com.bebas.module.base.core.dataPermission.enums.PermissionRangeEnums;
import com.bebas.module.base.web.service.ISysDeptService;
import com.bebas.module.base.web.service.ISysRoleDeptService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.org.bebasWh.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本部门以及以下 策略实现
 *
 * @author Wuhao
 * @date 2022/8/28 22:47
 */
@Service
public class PermissionRangeDept implements PerRangeStrategy {

    @Resource
    private ISysRoleDeptService sysRoleDeptService;
    @Resource
    private ISysDeptService sysDeptService;

    @Override
    public String getRangeSign() {
        return PermissionRangeEnums.DEPT.name();
    }

    @Override
    public void scopeFilter(JoinPoint joinPoint, LoginUser user, SysRoleModel role) {
        Long deptId = user.getDeptId();
        List<SysDeptModel> sysDeptList = sysDeptService.lambdaQuery().and(deptWrapper -> deptWrapper.eq(SysDeptModel::getId, deptId).or().apply(StringUtils.format("FIND_IN_SET({},{})", deptId, "ancestors"))).list();
        List<Long> deptIds = sysDeptList.stream().map(SysDeptModel::getId).collect(Collectors.toList());

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
