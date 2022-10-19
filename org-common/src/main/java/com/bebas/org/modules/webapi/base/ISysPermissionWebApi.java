package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.SysPermissionModel;

import java.util.List;
import java.util.Set;

/**
 * @author wuhao
 * @date 2022/10/3 15:09
 */
public interface ISysPermissionWebApi {

    /**
     * 获取所有控制器信息列表
     * @return
     */
    List<SysPermissionModel> getRouteList();

    /**
     * 获取用户的权限标识
     * @return
     */
    Set<String> getUserPermissionTag(Long userId);

}
