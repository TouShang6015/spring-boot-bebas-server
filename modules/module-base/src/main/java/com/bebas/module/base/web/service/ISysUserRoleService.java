package com.bebas.module.base.web.service;

import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserRoleModel;
import com.bebas.org.modules.webapi.base.ISysUserRoleWebApi;
import com.org.bebasWh.mapper.service.IService;

import java.util.List;

/**
 * 用户和角色关联表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysUserRoleService extends IService<SysUserRoleModel>, ISysUserRoleWebApi {

    /**
     * 查询角色通过用户名
     *
     * @param userName
     * @return
     */
    List<SysRoleModel> selectRolesByUserName(String userName);
}
