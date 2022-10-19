package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bebas.module.base.mapper.SysPostMapper;
import com.bebas.module.base.web.service.ISysUserPostService;
import com.org.bebasWh.exception.BusinessException;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.module.base.web.service.ISysPostService;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.bebas.org.modules.model.base.model.SysUserPostModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 岗位信息表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper,SysPostModel> implements ISysPostService {

    @Resource
    @Override
    protected void setMapper(SysPostMapper mapper) {
        super.mapper = mapper;
    }

    @Resource
    private ISysUserPostService sysUserPostService;

    @Override
    public boolean removeByIds(Collection<?> list) {
        List<SysUserPostModel> sysUserPostModelList = sysUserPostService.lambdaQuery().in(SysUserPostModel::getPostId, list).list();
        if (!CollUtil.isEmpty(sysUserPostModelList)){
            throw new BusinessException("已分配用户，无法删除");
        }
        return super.removeByIds(list);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param model 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostNameUnique(SysPostModel model) {
        Long postId = Objects.isNull(model.getId()) ? -1L : model.getId();
        List<SysPostModel> list = lambdaQuery().eq(SysPostModel::getPostName, model.getPostName()).ne(SysPostModel::getId,postId).list();
        return CollUtil.isEmpty(list);
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param model 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostCodeUnique(SysPostModel model) {
        Long postId = Objects.isNull(model.getId()) ? -1L : model.getId();
        List<SysPostModel> list = lambdaQuery().eq(SysPostModel::getPostCode, model.getPostCode()).ne(SysPostModel::getId,postId).list();
        return CollUtil.isEmpty(list);
    }
}
