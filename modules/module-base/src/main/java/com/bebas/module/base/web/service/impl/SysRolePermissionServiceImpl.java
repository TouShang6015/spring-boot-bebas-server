package com.bebas.module.base.web.service.impl;

import com.bebas.module.base.mapper.SysRolePermissionMapper;
import com.bebas.org.modules.model.base.model.SysRolePermissionModel;
import com.bebas.module.base.web.service.ISysRolePermissionService;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色和权限关联表 业务实现类
 *
 * @author WuHao
 * @date 2022-09-25 12:01:06
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper,SysRolePermissionModel> implements ISysRolePermissionService {

    @Resource
    protected void setMapper(SysRolePermissionMapper mapper) {
        super.mapper = mapper;
    }

}
