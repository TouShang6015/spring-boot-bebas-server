package com.bebas.module.base.web.service.impl;

import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.bebas.module.base.mapper.SysRoleMenuMapper;
import com.bebas.org.modules.model.base.model.SysRoleMenuModel;
import com.bebas.module.base.web.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色和菜单关联表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper,SysRoleMenuModel> implements ISysRoleMenuService {

    @Resource
    @Override
    protected void setMapper(SysRoleMenuMapper mapper) {
        super.mapper = mapper;
    }

}
