package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysPermissionModel;

import java.util.List;

/**
 * 权限管理 持久层接口
 *
 * @author WuHao
 * @date 2022-09-25 12:01:06
 */
public interface SysPermissionMapper extends BaseMapper<SysPermissionModel> {

    /**
     * 获取用户的已授权路由列表
     * @param userId
     * @return
     */
    List<SysPermissionModel> selectListByUserId(Long userId);
}
