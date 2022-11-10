package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bebas.module.base.mapper.SysMenuMapper;
import com.bebas.module.base.web.service.ISysMenuService;
import com.bebas.module.base.web.service.ISysRoleMenuService;
import com.bebas.module.base.web.service.ISysRoleService;
import com.bebas.module.base.web.service.ISysUserRoleService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.utils.tree.TreeService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.convert.base.SysMenuConvert;
import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.model.SysMenuModel;
import com.bebas.org.modules.model.base.model.SysRoleMenuModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.vo.menu.RouteMenuVo;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单权限表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuModel> implements ISysMenuService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ISysRoleMenuService sysRoleMenuService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private TreeService<SysMenuDTO> treeService;

    @Override
    public List<SysMenuModel> listByParam(SysMenuModel param) {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            Set<Long> roleIds = CollUtil.isEmpty(SecurityUtils.getRoleIds()) ? CollUtil.newHashSet(-1L) : SecurityUtils.getRoleIds();
            List<Long> menuIds = sysRoleMenuService.lambdaQuery().in(SysRoleMenuModel::getRoleId, roleIds).list().stream().map(SysRoleMenuModel::getMenuId).distinct().collect(Collectors.toList());
            param.queryParamIn(SysMenuModel::getId, menuIds);
        }
        return super.listByParam(param);
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuModel> selectMenuList(Long userId) {
        return selectMenuList(new SysMenuModel(), userId);
    }

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu   菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<SysMenuModel> selectMenuList(SysMenuModel menu, Long userId) {
        List<SysMenuModel> menuList = null;
        menu.setVisible(Constants.VISIBLE.SHOW);
        menu.setStatus(Constants.Status.NORMAL);
        // 管理员显示所有菜单信息
        if (SecurityUtils.isAdmin(userId)) {
            menuList = super.listByParam(menu);
        } else {
            menu.getParamExtMap().put("userId", userId);
            menuList = baseMapper.selectMenuListByUserId(menu);
        }
        return menuList;
    }

    /**
     * 根据权限获取菜单信息
     *
     * @return
     */
    @Override
    public List<SysMenuDTO> selectPermissionList(SysMenuModel param) {
        return SysMenuConvert.INSTANCE.convertToDTO(getPermissionMenuList(param));
    }

    /**
     * 根据权限获取菜单信息，转换成树结构
     *
     * @param param
     * @return
     */
    @Override
    public List<SysMenuDTO> selectPermissionTree(SysMenuModel param) {
        List<SysMenuDTO> menuList = SysMenuConvert.INSTANCE.convertToDTO(getPermissionMenuList(param));
        return treeService.convertTree(menuList);
    }

    /**
     * 根据权限获取菜单信息，转换成树结构模型
     *
     * @param param
     * @return
     */
    @Override
    public List<TreeModel> selectPermissionTreeModel(SysMenuModel param) {
        return selectPermissionTree(param).stream().map(TreeModel::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus
     * @return
     */
    @Override
    public List<TreeModel> buildMenuTreeSelect(List<SysMenuDTO> menus) {
        List<SysMenuDTO> list = treeService.convertTree(menus);
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        return list.stream().map(TreeModel::new).collect(Collectors.toList());
    }

    /**
     * 获取路由（前端进入页面时左侧路由）
     *
     * @param param
     * @return
     */
    @Override
    public List<RouteMenuVo> selectPermissionRoute(SysMenuModel param) {
        return selectPermissionTree(param)
                .stream()
                .sorted(Comparator.comparing(SysMenuDTO::getSort))
                .map(RouteMenuVo::new)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    @Override
    public List<Long> selectMenuListByRoleId(Long roleId) {
        SysRoleModel role = sysRoleService.getById(roleId);
        return baseMapper.selectMenuListByRoleId(roleId, role.getMenuCheckStrictly());
    }

    /**
     * 获取菜单列表
     *
     * @param param
     * @return
     */
    private List<SysMenuModel> getPermissionMenuList(SysMenuModel param) {
        Long userId = SecurityUtils.getUserId();
        param.setStatus(Constants.Status.NORMAL);
        param.setVisible(Constants.VISIBLE.SHOW);
        List<SysMenuModel> menuList = super.listByParam(param);
        if (SecurityUtils.isAdmin(userId)) {
            return menuList;
        } else {
            Set<Long> roleIds = SecurityUtils.getRoleIds();
            if (CollUtil.isEmpty(roleIds)) {
                return CollUtil.newArrayList();
            } else {
                List<SysRoleMenuModel> list = sysRoleMenuService.lambdaQuery().in(SysRoleMenuModel::getRoleId, roleIds).list();
                if (CollUtil.isEmpty(list)) {
                    return CollUtil.newArrayList();
                }
                List<Long> menus = list.parallelStream().map(SysRoleMenuModel::getMenuId).distinct().collect(Collectors.toList());
                menuList = menuList.parallelStream().filter(item -> menus.contains(item.getId())).collect(Collectors.toList());
                return menuList;
            }
        }
    }

    @Override
    public List<SysMenuModel> listByParamWebApi(SysMenuModel model) {
        return super.listByParam(model);
    }
}
