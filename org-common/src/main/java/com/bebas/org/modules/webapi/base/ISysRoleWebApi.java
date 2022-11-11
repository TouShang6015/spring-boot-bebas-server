package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.SysRoleModel;

import java.io.Serializable;

/**
 * 角色信息表 webapi
 *
 * @author WuHao
 * @date 2022/5/26 11:03
 */
public interface ISysRoleWebApi {

    SysRoleModel getById(Serializable id);

}
