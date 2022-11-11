package com.bebas.module.base.web.service;

import com.bebas.org.modules.model.base.model.SysRoleMenuModel;
import com.org.bebasWh.constants.RedisConstant;
import com.org.bebasWh.mapper.service.IService;
import com.org.bebasWh.mapper.utils.ModelUtil;

/**
 * 角色和菜单关联表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysRoleMenuService extends IService<SysRoleMenuModel> {

    String R_KEY = RedisConstant.NameSpace.MODULE_DATA + RedisConstant.NameSpace.TABLE + ModelUtil.getTableName(SysRoleMenuModel.class) + ":";

}
