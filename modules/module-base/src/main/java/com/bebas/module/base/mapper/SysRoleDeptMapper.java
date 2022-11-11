package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和部门关联表 持久层接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDeptModel> {

    /**
     * 获取部门id根据roleId
     *
     * @param roleId
     * @param b      部门树选择项是否关联显示
     * @return
     */
    List<Long> selectDeptIdsByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") Integer deptCheckStrictly);

}
