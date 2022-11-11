package com.bebas.module.base.web.service.impl;

import com.bebas.module.base.mapper.SysUserPostMapper;
import com.bebas.module.base.web.service.ISysUserPostService;
import com.bebas.org.modules.model.base.model.SysUserPostModel;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户与岗位关联表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPostModel> implements ISysUserPostService {
}
