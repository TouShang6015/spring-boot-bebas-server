package com.bebas.module.base.web.service;

import com.org.bebasWh.mapper.service.IService;
import com.bebas.org.modules.model.base.model.SysUserPostModel;
import com.org.bebasWh.constants.RedisConstant;
import com.org.bebasWh.mapper.utils.ModelUtil;

/**
 * 用户与岗位关联表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysUserPostService extends IService<SysUserPostModel> {

    String R_KEY = RedisConstant.NameSpace.MODULE_DATA + RedisConstant.NameSpace.TABLE + ModelUtil.getTableName(SysUserPostModel.class) + ":";

}
