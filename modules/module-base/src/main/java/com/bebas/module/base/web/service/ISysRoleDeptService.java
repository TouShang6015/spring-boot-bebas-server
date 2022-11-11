package com.bebas.module.base.web.service;

import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import com.org.bebasWh.mapper.service.IService;

import java.util.List;

/**
 * 角色和部门关联表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysRoleDeptService extends IService<SysRoleDeptModel> {

    /**
     * 获取部门id根据roleId
     *
     * @param roleId
     * @param deptCheckStrictly 部门树选择项是否关联显示
     * @return
     */
    List<Long> selectDeptIdsByRoleId(Long roleId, Integer deptCheckStrictly);
}
