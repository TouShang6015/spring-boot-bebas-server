package com.bebas.module.base.web.controller.auth;

import cn.hutool.core.util.ObjectUtil;
import com.bebas.module.base.core.auth.PcLoginWorker;
import com.bebas.module.base.web.service.ISysUserService;
import com.bebas.module.base.web.service.login.IRegisterService;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.vo.LoginPcRequest;
import com.bebas.org.modules.model.base.vo.RegisterBodyVo;
import com.bebas.org.modules.model.base.vo.user.UserInfo;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;
import com.org.bebasWh.exception.UserException;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 登陆控制器
 *
 * @author WuHao
 * @date 2022/5/31 21:44
 */
@RestController
@RequestMapping(ApiPrefixConstant.Auth.SYSTEM)
@Api(value = "Login", tags = "登陆控制器")
public class LoginPcController extends PcLoginWorker {

    @Resource
    private IRegisterService registerService;
    @Resource
    private ISysUserService sysUserService;

    public LoginPcController(ResourceConfigWebApi resourceConfigWebApi) {
        super(resourceConfigWebApi);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆", httpMethod = "POST", response = Result.class)
    @ApiImplicitParam(name = "param", value = "参数", dataType = "param", required = true, paramType = "body")
    public Result login(@RequestBody LoginPcRequest param) {
        this.flushConfig();
        // 123456
        // aIpYMEwSEoTZSdktaVHCE2Bf8sKH+7pRtae5iOheeIVP+xtBwfOLn7xGqrc2ZMuPlic3lUWalmxmg7svnHEHU6Pe3Ag91+RSOzRKHTGFIqkbo3PBabVhOCg+P4Sn4Q/q+Uz5ArqQFeGk0KCFRVPs1vvya9qvV9voKRw2siMQzQQ=
        if (openAuthCode()) {
            // todo 验证码 校验逻辑
        }
        LoginUser loginUser = super.doLogin(param);
        if (ObjectUtil.isNull(loginUser))
            throw new UserException("登录失败，用户信息异常！");
        String token = super.getToken(loginUser);
        return Result.success(token);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST", response = Result.class)
    @ApiImplicitParam(name = "param", value = "参数", dataType = "param", required = true, paramType = "body")
    public Result register(@RequestBody RegisterBodyVo param) {
        this.flushConfig();
        if (!this.mainVO.getRegisterOpen())
            return Result.fail(MessageUtils.message(MessageCode.System.SYSTEM_NOT_OPEN_REGISTER));
        return registerService.doRegister(param);
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户详细信息", httpMethod = "GET", response = Result.class)
    public Result info() {
        this.flushConfig();
        UserInfo userInfo = sysUserService.selectUserInfo(SecurityUtils.getUserId());
        Optional.ofNullable(userInfo.getUser()).ifPresent(item -> {
            item.setAvatar(this.mainVO.getStaticWebsite() + item.getAvatar());
        });
        return Result.success(userInfo);
    }

    /**
     * 是否开启验证码
     */
    @Override
    protected boolean openAuthCode() {
        return this.mainVO.getAuthCodeOpen();
    }

    /**
     * 获取最大登陆人数
     *
     * @return
     */
    @Override
    protected Integer getMaxLogin() {
        return this.mainVO.getMaxUserLogin();
    }

}
