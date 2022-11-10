package com.bebas.module.base.web.service.impl;

import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.bebas.module.base.mapper.SysUserRoleMapper;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserRoleModel;
import com.bebas.module.base.web.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和角色关联表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper,SysUserRoleModel> implements ISysUserRoleService {

    /**
     * 查询角色通过用户名
     *
     * @param userName
     * @return
     */
    @Override
    public List<SysRoleModel> selectRolesByUserName(String userName) {
        return baseMapper.selectRolesByUserName(userName);
    }

    @Override
    public List<SysUserRoleModel> listByParamWebApi(SysUserRoleModel param) {
        return super.listByParam(param);
    }
}
