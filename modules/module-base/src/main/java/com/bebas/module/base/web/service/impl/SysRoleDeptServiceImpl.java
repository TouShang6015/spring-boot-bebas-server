package com.bebas.module.base.web.service.impl;

import com.bebas.module.base.mapper.SysRoleDeptMapper;
import com.bebas.module.base.web.service.ISysRoleDeptService;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色和部门关联表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptMapper, SysRoleDeptModel> implements ISysRoleDeptService {

    /**
     * 获取部门id根据roleId
     *
     * @param roleId
     * @param deptCheckStrictly      部门树选择项是否关联显示
     * @return
     */
    @Override
    public List<Long> selectDeptIdsByRoleId(Long roleId, Integer deptCheckStrictly) {
        return baseMapper.selectDeptIdsByRoleId(roleId, deptCheckStrictly);
    }
}
