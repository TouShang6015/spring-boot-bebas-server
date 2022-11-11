package com.bebas.org.modules.model.base.dto;

import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 登录人token信息 Dto
 *
 * @author WuHao
 * @company Wuhao
 * @date 2022-06-01 11:19:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserTokenDTO extends SysUserTokenModel {

    private LoginUser loginUser;

}
