package com.bebas.module.base.web.service;

import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.model.SysMenuModel;
import com.bebas.org.modules.model.base.vo.menu.RouteMenuVo;
import com.bebas.org.modules.webapi.base.ISysMenuWebApi;
import com.org.bebasWh.mapper.service.IService;

import java.util.List;

/**
 * 菜单权限表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysMenuService extends IService<SysMenuModel>, ISysMenuWebApi {

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuModel> selectMenuList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuModel> selectMenuList(SysMenuModel menu, Long userId);

    /**
     * 根据权限获取菜单信息
     *
     * @return
     */
    List<SysMenuDTO> selectPermissionList(SysMenuModel param);

    /**
     * 根据权限获取菜单信息，转换成树结构
     *
     * @param param
     * @return
     */
    List<SysMenuDTO> selectPermissionTree(SysMenuModel param);

    /**
     * 根据权限获取菜单信息，转换成树结构模型
     *
     * @param param
     * @return
     */
    List<TreeModel> selectPermissionTreeModel(SysMenuModel param);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus
     * @return
     */
    List<TreeModel> buildMenuTreeSelect(List<SysMenuDTO> menus);

    /**
     * 获取路由（前端进入页面时左侧路由）
     *
     * @param param
     * @return
     */
    List<RouteMenuVo> selectPermissionRoute(SysMenuModel param);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Long> selectMenuListByRoleId(Long roleId);
}
