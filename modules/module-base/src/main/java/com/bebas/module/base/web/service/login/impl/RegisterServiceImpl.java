package com.bebas.module.base.web.service.login.impl;

import cn.hutool.core.collection.CollUtil;
import com.bebas.module.base.web.service.ISysUserService;
import com.bebas.module.base.web.service.login.IRegisterService;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.constants.SecurityConstant;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.modules.convert.base.SysUserConvert;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.vo.RegisterBodyVo;
import com.org.bebasWh.utils.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author WuHao
 * @date 2022/5/29 16:26
 */
@Service
@Validated
public class RegisterServiceImpl implements IRegisterService {

    @Resource
    private ISysUserService sysUserService;

    /**
     * 注册接口
     * <p>
     * userName -- 必填
     * nickName -- 必填
     * password -- 密码
     * </p>
     *
     * @param param
     * @return
     */
    @Override
    public Result doRegister(@Valid RegisterBodyVo param) {
        // 检验是否存在当前用户
        if (sysUserService.countByParam(SysUserDTO.builder().userName(param.getUserName()).build()) > 0) {
            return Result.fail(MessageUtils.message(MessageCode.User.USER_UNIQUE));
        }
        // 设置用户参数
        SysUserDTO _insertParam = SysUserConvert.INSTANCE.convertToRegister(param);
        _insertParam.setDeptId(1L);
        _insertParam.setRoleIds(CollUtil.newArrayList(SecurityConstant.SYSTEM_ID));
        if (sysUserService.insertUser(_insertParam) > 0) {
            return Result.success(MessageUtils.message(MessageCode.User.USER_REGISTER_SUCCESS));
        }
        return Result.fail(MessageUtils.message(MessageCode.User.USER_REGISTER_FAIL));
    }
}
