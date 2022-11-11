package com.bebas.org.common.security.service;

import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.security.vo.LoginUser;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.bebas.org.modules.model.base.model.SysUserRoleModel;
import com.bebas.org.modules.webapi.base.ISysPermissionWebApi;
import com.bebas.org.modules.webapi.base.ISysUserRoleWebApi;
import com.bebas.org.modules.webapi.base.ISysUserWebApi;
import com.org.bebasWh.exception.UserException;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户验证处理
 *
 * @author WhHao
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Resource
    private ISysUserWebApi sysUserWebApi;

    @Resource
    private ISysPermissionWebApi sysPermissionWebApi;
    @Autowired
    private ISysUserRoleWebApi sysUserRoleWebApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserModel user = sysUserWebApi.selectUserByUserName(username);
        if (StringUtils.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UserException("登录用户：" + username + " 不存在");
        } else if (Constants.DelFlag.DEL.equals(String.valueOf(user.getDelFlag()))) {
            log.info("登录用户：{} 已被删除.", username);
            throw new UserException("对不起，您的账号：" + username + " 已被删除");
        } else if (Constants.Disable.DISABLE.equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new UserException("对不起，您的账号：" + username + " 已停用");
        }
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUserModel user) {
        LoginUser loginUser = new LoginUser(user.getId(), user.getDeptId(), user, sysPermissionWebApi.getUserPermissionTag(user.getId()));
        SysUserRoleModel queryParam = new SysUserRoleModel();
        queryParam.setUserId(user.getId());
        List<SysUserRoleModel> userRoleList = OptionalUtil.ofNullList(sysUserRoleWebApi.listByParamWebApi(queryParam));
        loginUser.setRoleIds(userRoleList.stream().map(SysUserRoleModel::getRoleId).collect(Collectors.toSet()));
        return loginUser;
    }
}
