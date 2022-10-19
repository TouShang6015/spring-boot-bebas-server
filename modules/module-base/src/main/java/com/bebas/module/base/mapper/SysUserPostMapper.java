package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.org.modules.model.base.model.SysUserPostModel;

import java.util.List;

/**
 * 用户与岗位关联表 持久层接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPostModel> {

    List<SysPostModel> selectPostByUserId(Long userId);

}
