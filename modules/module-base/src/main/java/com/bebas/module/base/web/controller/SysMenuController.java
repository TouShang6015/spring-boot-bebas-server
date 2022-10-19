package com.bebas.module.base.web.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONArray;
import com.org.bebasWh.utils.OptionalUtil;
import com.bebas.module.base.web.service.ISysRoleMenuService;
import com.org.bebasWh.enums.ConditionEnum;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.convert.base.SysMenuConvert;
import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.model.SysMenuModel;
import com.bebas.module.base.web.service.ISysMenuService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.model.SysRoleMenuModel;
import com.bebas.org.modules.model.base.vo.menu.RouteMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysmenu")
@Api(value = "SysMenuModel",tags = "菜单权限")
public class SysMenuController extends BaseController<ISysMenuService,SysMenuModel> {

    @Resource
    public void setService(ISysMenuService service) {
        super.service = service;
    }

    @Resource
    private ISysRoleMenuService sysRoleMenuService;

    /**
     * 根据权限获取菜单信息，转换成树结构
     * @return
     */
    @PostMapping("/selectPermissionTree")
    @ApiOperation(value = "根据权限获取菜单信息，转换成树结构", notes = "根据权限获取菜单信息，转换成树结构", httpMethod = "POST", response = Result.class)
    @ApiOperationSupport(order = 10)
    public Result selectPermissionTree(@RequestBody SysMenuModel param){
        List<SysMenuDTO> sysMenuDTOS = service.selectPermissionTree(param);
        return Result.success(sysMenuDTOS);
    }

    /**
     * 根据权限获取菜单信息，转换成树结构模型
     * @return
     */
    @PostMapping("/selectPermissionTreeModel")
    @ApiOperation(value = "根据权限获取菜单信息，转换成树结构模型", notes = "根据权限获取菜单信息，转换成树结构模型", httpMethod = "POST", response = Result.class)
    @ApiOperationSupport(order = 11)
    public Result selectPermissionTreeModel(@RequestBody SysMenuModel param){
        List<TreeModel> treeModels = service.selectPermissionTreeModel(param);
        return Result.success(treeModels);
    }

    /**
     * 获取路由（前端进入页面时左侧路由）
     * @param param
     * @return
     */
    @PostMapping("/selectPermissionRoute")
    @ApiOperation(value = "获取路由（前端进入页面时左侧路由）", notes = "获取路由（前端进入页面时左侧路由）", httpMethod = "POST", response = Result.class)
    @ApiOperationSupport(order = 12)
    public Result selectPermissionRoute(@RequestBody SysMenuModel param){
        param.getParamExtMap().put("menuType",String.join(StringPool.COMMA, CollUtil.newArrayList(Constants.MENU_TYPE.C, Constants.MENU_TYPE.M)));
        param.getQueryCondition().put("menuType", ConditionEnum.IN.name());
        param.getSortCondition().put("orderNum", true);
        List<RouteMenuVo> list = service.selectPermissionRoute(param);
        return Result.success(JSONArray.parse(JSONArray.toJSONString(list)));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @ApiOperation(value = "加载对应角色菜单列表树", httpMethod = "GET", response = Result.class)
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public Result roleMenuTreeselect(@PathVariable("roleId") Long roleId){
        List<SysMenuModel> menus = service.selectMenuList(SecurityUtils.getUserId());
        List<SysMenuDTO> menuDtos = SysMenuConvert.INSTANCE.convertToDTO(menus);
        Map<String, Object> map = new HashMap<>();
        map.put("checkedKeys", OptionalUtil.ofNullList(service.selectMenuListByRoleId(roleId)).stream().map(String::valueOf).collect(Collectors.toList()));
        map.put("menus", service.buildMenuTreeSelect(menuDtos));
        return Result.success(map);
    }

    @Log(title = "菜单新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysMenuDTO param = MapperUtil.convert(m, SysMenuDTO.class);
        Long menuId = Objects.isNull(param.getId()) ? -1L : param.getId();
        List<SysMenuModel> menuModelList = service.listByParam(SysMenuModel.builder().menuName(param.getMenuName()).parentId(param.getParentId()).build());
        if ( !CollUtil.isEmpty(menuModelList) && !Objects.equals(menuModelList.get(NumberUtils.INTEGER_ZERO).getId(), menuId)) {
            return Result.fail("新增菜单失败，菜单名字已存在！");
        }else if (Constants.BOOLEAN.TRUE.equals(param.getStateFrame()) && !StringUtils.ishttp(param.getPath())) {
            return Result.fail("新增菜单失败，地址必须以http(s)://开头");
        }
        return super.baseAdd(param);
    }

    @Log(title = "菜单编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysMenuDTO param = MapperUtil.convert(m, SysMenuDTO.class);
        Long menuId = Objects.isNull(param.getId()) ? -1L : param.getId();
        List<SysMenuModel> menuModelList = service.listByParam(SysMenuModel.builder().menuName(param.getMenuName()).parentId(param.getParentId()).build());
        if ( !CollUtil.isEmpty(menuModelList) && !Objects.equals(menuModelList.get(NumberUtils.INTEGER_ZERO).getId(), menuId)) {
            return Result.fail("新增菜单失败，菜单名字已存在！");
        }else if (Constants.BOOLEAN.TRUE.equals(param.getStateFrame()) && !StringUtils.ishttp(param.getPath())) {
            return Result.fail("新增菜单失败，地址必须以http(s)://开头");
        }else if (param.getId().equals(param.getParentId())) {
            return Result.fail("修改菜单失败，上级菜单不能选择自己");
        }
        return super.baseEdit(param);
    }

    @Log(title = "菜单删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toList());
        List<SysMenuModel> menuModelList = service.lambdaQuery().in(SysMenuModel::getParentId, idList).list();
        if (!CollUtil.isEmpty(menuModelList) && menuModelList.size() > 0){
            return Result.fail("存在子菜单,不允许删除");
        }
        List<SysRoleMenuModel> sysRoleMenuModelList = sysRoleMenuService.lambdaQuery().in(SysRoleMenuModel::getMenuId, ids).list();
        if (!CollUtil.isEmpty(sysRoleMenuModelList) && sysRoleMenuModelList.size() > 0){
            return Result.fail("菜单已分配,不允许删除");
        }
        return super.baseDeleteByIds(ids);
    }
}
