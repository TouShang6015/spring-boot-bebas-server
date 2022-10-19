package com.bebas.module.base.web.controller;

import com.org.bebasWh.enums.result.ResultEnum;
import com.org.bebasWh.utils.result.Result;
import com.bebas.module.base.web.service.IBaseResourceConfigService;
import com.bebas.module.base.web.service.ISysUserService;
import com.bebas.org.common.constants.ResourceConfigConstant;
import com.bebas.org.common.enums.BaseEnums;
import com.bebas.org.common.enums.file.FileHeaderImageEnum;
import com.bebas.org.common.enums.file.FilePrefixEnum;
import com.bebas.org.common.security.service.TokenService;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.framework.fileManager.service.FileService;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.bebas.org.modules.model.base.vo.baseResource.ResourceMainVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Optional;

/**
 * 个人业务处理
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/profile")
@Api(tags = "个人业务")
public class SysProfileController {

    @Resource
    private ISysUserService userService;
    @Resource
    private TokenService tokenService;
    @Resource
    private IBaseResourceConfigService baseResourceConfigService;
    @Resource
    private FileService fileService;

    @ApiOperation(value = "个人信息", httpMethod = "GET", response = Result.class)
    @GetMapping
    public Result profile() {
        Result result = Result.success();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUserDTO userInfo = userService.selectUserDetailById(loginUser.getUserId());
        Optional.ofNullable(userInfo).ifPresent(item -> {
            ResourceMainVO resourceMainVO = baseResourceConfigService.queryValueByConfigKey(ResourceConfigConstant.MAIN_KEY, ResourceMainVO.class);
            item.setAvatar(resourceMainVO.getStaticWebsite() + item.getAvatar());
        });
        result.put("userInfo", userInfo);
        result.put("roleGroup", userService.selectUserRoleGroup(userInfo.getUserName()));
        result.put("postGroup", userService.selectUserPostGroup(userInfo.getUserName()));
        return result;
    }

    @Log(title = "修改个人信息")
    @ApiOperation(value = "修改个人信息", httpMethod = "PUT", response = Result.class)
    @PutMapping("/update")
    public Result update(@RequestBody SysUserModel param) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUserModel userModel = loginUser.getUserModel();
        if (!userService.checkPhoneUnique(param)) {
            return Result.fail(MessageUtils.message("business.base.user.add.phonenumber.unique"));
        }
        if (!userService.checkEmailUnique(param)) {
            return Result.fail(MessageUtils.message("business.base.user.add.email.unique"));
        }
        param.setId(userModel.getId());
        param.setPassword(null);
        param.setUserName(null);
        if (!userService.updateById(param)) {
            return Result.fail(ResultEnum.FAIL_UPDATE);
        }
        // 更新缓存用户信息
        userModel.setNickName(param.getNickName());
        userModel.setPhonenumber(param.getPhonenumber());
        userModel.setEmail(param.getEmail());
        userModel.setSex(param.getSex());
        tokenService.setLoginUser(loginUser);
        return Result.success();
    }

    @Log(title = "修改密码")
    @PutMapping("/updatePwd")
    public Result resetPassword(String oldPassword, String newPassword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return Result.fail("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return Result.fail("新密码不能与旧密码相同");
        }
        if (!userService.resetUserPwd(userName, newPassword)) {
            return Result.fail("修改密码异常，请联系管理员");
        }
        // 更新缓存用户密码
        loginUser.getUserModel().setPassword(SecurityUtils.encryptPassword(newPassword));
        tokenService.setLoginUser(loginUser);
        return Result.success();
    }

    @Log(title = "修改个人信息")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody SysUserModel param) {
        param.setId(SecurityUtils.getUserId());
        param.setPassword(null);
        param.setUserName(null);
        param.setUserType(null);
        if (!userService.updateById(param)) {
            return Result.fail(ResultEnum.FAIL_UPDATE);
        }
        return Result.success(ResultEnum.SUCCESS_UPDATE);
    }


    /**
     * 头像上传
     */
    @Log(title = "用户头像上传")
    @PostMapping("/avatar")
    public Result avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String suffix = BaseEnums.getValueByKey.apply(FileHeaderImageEnum.values(), file.getContentType());
            String path = fileService.upload(FilePrefixEnum.UPLOAD.getKey(),suffix,file.getInputStream());
            SysUserModel model = userService.getById(SecurityUtils.getUserId());
            model.setAvatar(path);
            if (userService.updateById(model)) {
                Result result = Result.success();
                result.put("imgUrl", path);
                // 更新缓存用户头像
                loginUser.setUserModel(model);
                tokenService.setLoginUser(loginUser);
                return result;
            }
        }
        return Result.fail("上传图片异常，请联系管理员");
    }

}
