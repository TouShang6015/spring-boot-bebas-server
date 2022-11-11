package com.bebas.module.base.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.module.base.web.service.ISysDeptService;
import com.bebas.module.base.web.service.ISysRoleDeptService;
import com.bebas.module.base.web.service.ISysRoleService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.utils.tree.TreeService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.convert.base.SysDeptConvert;
import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.org.bebasWh.enums.result.ResultEnum;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.page.PageUtil;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 部门表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysdept")
@Api(value = "SysDeptModel", tags = "部门管理")
public class SysDeptController extends BaseController<ISysDeptService, SysDeptModel> {

    @Resource
    private ISysRoleDeptService sysRoleDeptService;
    @Resource
    private ISysRoleService sysRoleService;

    @Resource
    private TreeService<SysDeptDTO> treeService;

    @Override
    protected Result baseQueryById(@PathVariable Long id) {
        Result result = Result.success();
        Optional.ofNullable(service.getById(id)).ifPresent(model -> {
            result.setData(SysDeptConvert.INSTANCE.convertToDTO(model));
        });
        return result;
    }

    @Override
    protected Result baseQueryByParam(@RequestBody(required = false) SysDeptModel param) {
        Result result = Result.success();
        Optional.ofNullable(service.listByParam(param)).ifPresent(list -> {
            result.setData(SysDeptConvert.INSTANCE.convertToDTO(list));
        });
        return result;
    }

    @Override
    protected Result baseQueryPageByParam(@RequestBody(required = false) SysDeptModel param) {
        Result result = Result.success();
        IPage<SysDeptModel> page = PageUtil.pageBean(param);
        Optional.ofNullable(service.listPageByParam(page, param)).ifPresent(list -> {
            List<SysDeptModel> records = list.getRecords();
            result.setData(SysDeptConvert.INSTANCE.convertToDTO(records));
        });
        return result;
    }

    @Log(title = "部门新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysDeptDTO param = MapperUtil.convert(m, SysDeptDTO.class);
        if (!service.checkDeptNameUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.DEPT_NAME_EXISTS_HANDLE_FAIL));
        }
        if (!service.insertDept(param)) {
            return Result.fail(ResultEnum.FAIL_INSERT);
        }
        return Result.success(ResultEnum.SUCCESS_INSERT);
    }

    @Log(title = "部门编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysDeptDTO param = MapperUtil.convert(m, SysDeptDTO.class);
        Long deptId = param.getId();
        service.checkDeptDataScope(deptId);
        if (!service.checkDeptNameUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.DEPT_NAME_EXISTS_HANDLE_FAIL));
        } else if (param.getParentId().equals(deptId)) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.PARENT_NOT_SELF_HANDLE_FAIL));
        } else if (Constants.Status.NO_NORMAL.equals(param.getStatus()) && service.selectNormalChildrenDeptById(deptId) > 0) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.DEPT_INCLUDE_NOT_CLOSE_CHILDREN));
        }
        service.updateDept(param);
        return Result.success();
    }

    @Log(title = "部门删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<Long> deptIdList = StringUtils.splitToList(ids, Long::valueOf);
        if (service.hasChildByDeptId(deptIdList)) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.EXISTS_CHILD_DEPT_NOT_HANDLE));
        }
        if (service.checkDeptExistUser(deptIdList)) {
            return Result.fail(MessageUtils.message(MessageCode.Dept.EXISTS_CHILD_USER_NOT_HANDLE));
        }
        return super.baseDeleteByIds(ids);
    }

    @ApiOperation(value = "查询部门列表（排除节点）", httpMethod = "GET", response = Result.class)
    @GetMapping("/list/exclude/{deptId}")
    public Result excludeChild(@PathVariable Long deptId) {
        List<SysDeptModel> list = OptionalUtil.ofNullList(service.listByParam(new SysDeptModel())).stream()
                .filter(item -> !(item.getId().equals(deptId) || StringUtils.splitToList(item.getAncestors(), String::valueOf).contains(deptId.toString())))
                .collect(Collectors.toList());
        return Result.success(list);
    }

    @ApiOperation(value = "获取部门下拉树列表", httpMethod = "GET", response = Result.class)
    @GetMapping("/treeselect")
    public Result treeselect(SysDeptModel param) {
        Long parentId = param.getParentId();
        param.setParentId(null);
        List<SysDeptModel> list = service.listByParam(param);
        List<SysDeptDTO> treeList = this.treeService.convertTree(SysDeptConvert.INSTANCE.convertToDTO(list));
        if (Objects.nonNull(parentId) && !parentId.equals(Constants.DEFAULT_PARENT_ID)) {
            treeList = treeService.filterParent(treeList, parentId);
        }
        List<TreeModel> treeModelList = treeList.stream().map(TreeModel::new).collect(Collectors.toList());
        return Result.success(treeModelList);
    }

    @ApiOperation(value = "加载对应角色部门列表树", httpMethod = "GET", response = Result.class)
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public Result roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
        Result result = Result.success();
        List<SysDeptDTO> deptDtoList = SysDeptConvert.INSTANCE.convertToDTO(service.listByParam(new SysDeptModel()));
        List<TreeModel> treeModelList = treeService.convertTree(deptDtoList).stream().map(TreeModel::new).collect(Collectors.toList());
        SysRoleModel role = sysRoleService.getById(roleId);
        List<String> deptIds = OptionalUtil.ofNullList(sysRoleDeptService.selectDeptIdsByRoleId(roleId, role.getDeptCheckStrictly())).stream().map(String::valueOf).collect(Collectors.toList());
        result.put("checkedKeys", deptIds);
        result.put("depts", treeModelList);
        return result;
    }

}
