package com.bebas.module.base.web.service;

import com.org.bebasWh.mapper.service.IService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.bebas.org.modules.model.base.model.SysPermissionModel;

import java.util.List;

/**
 * 权限管理 业务接口
 *
 * @author WuHao
 * @date 2022-09-25 12:01:06
 */
public interface ISysPermissionService extends IService<SysPermissionModel> {

    /**
     * 获取项目路由地址
     * @return
     */
    List<SysPermissionModel> getProjectRequestMapping();

    /**
     * 接口路由同步
     * @return
     */
    boolean handleMappingSync();

    /**
     * 分配路由模块
     * @param parentId
     * @param permissionModelList
     * @return
     */
    boolean handleAllocationRouteModule(Long parentId, List<SysPermissionModel> permissionModelList);

    /**
     * 构建树结构列表
     * @param dtoList
     * @return
     */
    List<TreeModel> buildTreePermissionList(List<SysPermissionDTO> dtoList);

    /**
     * 获取角色的路由列表
     * @param roleId
     * @return
     */
    List<Long> rolePermissionByRoleId(Long roleId);

    /**
     * 刷新security动态权限
     */
    void flushPermissionConfig();
}
