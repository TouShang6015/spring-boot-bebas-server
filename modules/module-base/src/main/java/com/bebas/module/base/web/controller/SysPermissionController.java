package com.bebas.module.base.web.controller;

import com.bebas.module.base.web.service.ISysPermissionService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.convert.base.SysPermissionConvert;
import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.bebas.org.modules.model.base.model.SysPermissionModel;
import com.bebas.org.modules.model.base.vo.permission.AllocationModuleParamVO;
import com.org.bebasWh.core.validator.group.Update;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bebas.org.common.constants.MessageCode.Permission.NOT_ADD_ROUTE;

/**
 * @author Wuhao
 * @date 2022/9/25 17:21
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/syspermission")
@Api(value = "SysPermissionModel", tags = "权限管理")
public class SysPermissionController extends BaseController<ISysPermissionService, SysPermissionModel> {

    @Override
    protected Result baseQueryByParam(@RequestBody SysPermissionModel param) {
        List<SysPermissionDTO> list = SysPermissionConvert.INSTANCE.convertToDTO(service.listByParam(param));
        Map<String, List<SysPermissionDTO>> moduleControllerGroupMap = OptionalUtil.ofNullList(list).parallelStream().collect(Collectors.groupingBy(SysPermissionDTO::getModuleController));
        List<SysPermissionDTO> finalList = moduleControllerGroupMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        return Result.success(finalList);
    }

    @Log(title = "接口路由同步")
    @ApiOperation(value = "接口路由同步", httpMethod = "POST", response = Result.class)
    @PostMapping("/mappingSync")
    public Result mappingSync() {
        return Result.successBoolean(service.handleMappingSync());
    }

    @Log(title = "分配路由模块")
    @ApiOperation(value = "分配路由模块", httpMethod = "POST", response = Result.class)
    @PostMapping("/allocationRouteModule")
    public Result allocationRouteModule(@RequestBody @Valid AllocationModuleParamVO param) {
        Long parentId = param.getParentId();
        List<SysPermissionModel> permissionModelList = param.getPermissionModelList();
        return Result.successBoolean(service.handleAllocationRouteModule(parentId, permissionModelList));
    }

    @Log(title = "改变接口访问规则")
    @ApiOperation(value = "改变接口访问规则", httpMethod = "PUT", response = Result.class)
    @PutMapping("/changeRouteVisitRule")
    public Result changeRouteVisitRule(@RequestBody @Validated({Update.class}) SysPermissionModel param) {
        if (service.updateById(param)) {
            return Result.success(param);
        }
        return Result.fail();
    }


    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysPermissionModel model = MapperUtil.convert(m, SysPermissionModel.class);
        if (model.getIfRoute().equals(Integer.valueOf(Constants.BOOLEAN.TRUE))) {
            return Result.fail(MessageUtils.message(NOT_ADD_ROUTE));
        }
        return super.baseAdd(model);
    }

    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysPermissionModel model = MapperUtil.convert(m, SysPermissionModel.class);
        model.setRoutePath(null);
        model.setOriginalRoutePath(null);
        model.setPermissionTag(null);
        model.setIfRoute(null);
        return super.baseEdit(model);
    }

    @Override
    protected Result baseDeleteByIds(@PathVariable String ids) {
        List<Long> idList = StringUtils.splitToList(ids, Long::valueOf);
        if (service.lambdaQuery().in(SysPermissionModel::getParentId, idList).count() > 0) {
            return Result.fail(MessageUtils.message(MessageCode.System.EXISTS_DOWN_TYPE_NOT_HANDLE));
        }
        return super.baseDeleteByIds(ids);
    }

    @GetMapping("/rolePermissionTreeList")
    @ApiOperation(value = "获取路由树列表（根据角色）", httpMethod = "GET", response = Result.class)
    public Result rolePermissionTreeList(SysPermissionModel param) {
        List<SysPermissionDTO> dtoList = SysPermissionConvert.INSTANCE.convertToDTO(OptionalUtil.ofNullList(service.listByParam(param)));
        List<TreeModel> treeModelList = service.buildTreePermissionList(dtoList);
        return Result.success(treeModelList);
    }

    @GetMapping("/rolePermissionByRoleId/{roleId}")
    @ApiOperation(value = "获取角色拥有的路由id", httpMethod = "GET", response = Result.class)
    public Result rolePermissionByRoleId(@PathVariable("roleId") Long roleId) {
        List<Long> permissionIds = service.rolePermissionByRoleId(roleId);
        return Result.success(permissionIds);
    }

    @Log(title = "刷新路由动态权限")
    @ApiOperation(value = "刷新路由动态权限", httpMethod = "GET", response = Result.class)
    @GetMapping("/flushPermissionConfig")
    public Result flushPermissionConfig() {
        service.flushPermissionConfig();
        return Result.success();
    }

}
