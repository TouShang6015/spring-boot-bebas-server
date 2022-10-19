package com.bebas.module.base.web.service.impl;

import com.bebas.module.base.mapper.BaseMaterialInfoMapper;
import com.bebas.org.modules.model.base.model.BaseMaterialInfoModel;
import com.bebas.module.base.web.service.IBaseMaterialInfoService;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 素材管理 业务实现类
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 */
@Service
public class BaseMaterialInfoServiceImpl extends ServiceImpl<BaseMaterialInfoMapper,BaseMaterialInfoModel> implements IBaseMaterialInfoService {

    @Resource
    protected void setMapper(BaseMaterialInfoMapper mapper) {
        super.mapper = mapper;
    }

    @Override
    public boolean save(BaseMaterialInfoModel entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(BaseMaterialInfoModel entity) {
        return super.updateById(entity);
    }
}
