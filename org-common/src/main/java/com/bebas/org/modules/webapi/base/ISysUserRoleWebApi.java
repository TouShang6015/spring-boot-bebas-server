package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.SysUserRoleModel;

import java.util.List;

/**
 * @author wuhao
 * @date 2022/9/6 16:16
 */
public interface ISysUserRoleWebApi {

    List<SysUserRoleModel> listByParamWebApi(SysUserRoleModel param);

}
