package com.bebas.module.base.web.controller;

import com.bebas.module.base.web.service.ISysPostService;
import com.bebas.module.base.web.service.ISysRoleService;
import com.bebas.module.base.web.service.ISysUserRoleService;
import com.bebas.module.base.web.service.ISysUserService;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.convert.base.SysUserConvert;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.bebas.org.modules.model.base.model.SysUserRoleModel;
import com.bebas.org.modules.model.base.vo.excel.SysUserExcelVo;
import com.org.bebasWh.enums.result.ResultEnum;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户信息表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysuser")
@Api(value = "SysUserModel", tags = "用户信息")
public class SysUserController extends BaseController<ISysUserService, SysUserModel> {

    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysPostService sysPostService;

    @Override
    protected Result baseQueryById(@PathVariable(value = "id", required = false) Long id) {
        Result result = Result.success();
        List<SysRoleModel> roleList = sysRoleService.list();
        result.put("roles", SecurityUtils.isAdmin(id) ? roleList : roleList.stream().filter(r -> !SecurityUtils.isAdmin(r.getId())).collect(Collectors.toList()));
        result.put("posts", sysPostService.list());
        if (!Objects.isNull(id)) {
            Optional.ofNullable(service.selectUserById(id)).ifPresent(userDTO -> {
                result.put("user", userDTO);
                result.put("postIds", OptionalUtil.ofNullList(userDTO.getPostList()).parallelStream().map(SysPostModel::getId).distinct().collect(Collectors.toList()));
                result.put("roleIds", OptionalUtil.ofNullList(userDTO.getRoleList()).parallelStream().map(SysRoleModel::getId).distinct().collect(Collectors.toList()));
            });
        }
        return result;
    }

    @Log(title = "用户新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysUserDTO param = MapperUtil.convert(m, SysUserDTO.class);
        if (!service.checkUserNameUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.USER_NAME_EXISTS_HANDLE_FAIL));
        }
        if (!service.checkPhoneUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.PHONE_EXISTS_HANDLE_FAIL));
        }
        if (!service.checkEmailUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.EMAIL_EXISTS_HANDLE_FAIL));
        }
        service.insertUser(param);
        return Result.success(ResultEnum.SUCCESS_INSERT);
    }

    @Log(title = "用户编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysUserDTO param = MapperUtil.convert(m, SysUserDTO.class);
        if (SecurityUtils.isAdmin(param.getId())) {
            return Result.fail(MessageUtils.message(MessageCode.Role.SYSTEM_ROLE_NOT_HANDLE));
        }
        if (!service.checkUserNameUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.USER_NAME_EXISTS_HANDLE_FAIL));
        }
        if (!service.checkPhoneUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.PHONE_EXISTS_HANDLE_FAIL));
        }
        if (!service.checkEmailUnique(param)) {
            return Result.fail(MessageUtils.message(MessageCode.User.EMAIL_EXISTS_HANDLE_FAIL));
        }
        param.setPassword(null);
        service.updateUser(param);
        return Result.success(ResultEnum.SUCCESS_UPDATE);
    }

    @Log(title = "用户删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<Long> idList = StringUtils.splitToList(ids, Long::valueOf);
        Long adminUserId = idList.parallelStream().filter(SecurityUtils::isAdmin).findFirst().orElse(null);
        if (Objects.nonNull(adminUserId)) {
            return Result.fail(MessageUtils.message(MessageCode.Role.SYSTEM_ROLE_NOT_REMOVE));
        }
        return Result.successBoolean(service.removeByIds(idList));
    }

    @Log(title = "用户重置密码")
    @ApiOperation(value = "重置密码", httpMethod = "PUT", response = Result.class)
    @PutMapping("/resetPwd")
    public Result resetPwd(@RequestBody SysUserModel param) {
        service.checkUserAllowed(param);
        param.setPassword(SecurityUtils.encryptPassword(param.getPassword()));
        return Result.successBoolean(service.updateById(param));
    }

    @Log(title = "用户状态修改")
    @ApiOperation(value = "状态修改", httpMethod = "PUT", response = Result.class)
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysUserModel param) {
        service.checkUserAllowed(param);
        return Result.successBoolean(service.updateById(param));
    }

    @ApiOperation(value = "根据用户编号获取授权角色", httpMethod = "GET", response = Result.class)
    @GetMapping("/authRole/{id}")
    public Result authRole(@PathVariable("id") Long id) {
        Result result = Result.success();
        List<Long> roleIds = OptionalUtil.ofNullList(
                        sysUserRoleService.lambdaQuery().eq(SysUserRoleModel::getUserId, id).list()
                ).stream()
                .map(SysUserRoleModel::getRoleId)
                .distinct()
                .collect(Collectors.toList());
        List<SysRoleModel> roles = OptionalUtil.ofNullList(sysRoleService.listByParam(new SysRoleModel())).stream()
                .filter(item -> roleIds.contains(item.getId()))
                .collect(Collectors.toList());
        if (!SecurityUtils.isAdmin(id)) {
            roles = roles.stream().filter(item -> !SecurityUtils.isAdmin(item.getId())).collect(Collectors.toList());
        }
        result.put("user", service.getById(id));
        result.put("roles", roles);
        return result;
    }

    @Log(title = "用户授权角色")
    @ApiOperation(value = "用户授权角色", httpMethod = "PUT", response = Result.class)
    @PutMapping("/insertAuthRole")
    public Result insertAuthRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") List<Long> roleIds) {
        SysUserDTO queryParam = new SysUserDTO();
        queryParam.setId(userId);
        service.checkUserAllowed(queryParam);
        queryParam.setRoleIds(roleIds);
        return Result.successBoolean(service.updateUser(queryParam) > 0);
    }

    @Log(title = "用户导出")
    @ApiOperation(value = "用户导出", httpMethod = "POST", response = Result.class)
    @PostMapping("/excelExport")
    protected void executeExcelExport(SysUserModel param, HttpServletResponse response) throws IOException {
        List<SysUserDTO> userModelList = service.selectDetailList(SysUserConvert.INSTANCE.convertToDTO(param));
        List<SysUserExcelVo> excelList = userModelList.stream().map(SysUserConvert.INSTANCE::convertToExcel).collect(Collectors.toList());
        super.executeExcelExportDownload("用户导出", excelList, SysUserExcelVo.class, response);
    }
}
