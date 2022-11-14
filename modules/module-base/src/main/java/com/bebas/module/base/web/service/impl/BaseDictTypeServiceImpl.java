package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.bebas.module.base.mapper.BaseDictTypeMapper;
import com.bebas.module.base.web.service.IBaseDictDataService;
import com.bebas.module.base.web.service.IBaseDictTypeService;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.modules.model.base.model.BaseDictDataModel;
import com.bebas.org.modules.model.base.model.BaseDictTypeModel;
import com.org.bebasWh.constants.RedisConstant;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.mapper.utils.ModelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典类型表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@Service
public class BaseDictTypeServiceImpl extends ServiceImpl<BaseDictTypeMapper, BaseDictTypeModel> implements IBaseDictTypeService {

    private final String KEY_TYPE = ModelUtil.modelMainKey(BaseDictTypeModel.class) + "@dictType-";

    private final String KEY_KEYWORD = ModelUtil.modelMainKey(BaseDictTypeModel.class) + RedisConstant.Keyword.ID;

    @Resource
    private RedisCache redisCache;
    @Resource
    private IBaseDictDataService dictDataService;

    /**
     * 根据字典类型获取
     *
     * @param dictType
     * @return
     */
    @Override
    public BaseDictTypeModel selectOneByType(String dictType) {
        BaseDictTypeModel data = cacheGetByDictType(dictType);
        if (ObjectUtil.isNull(data)) {
            data = lambdaQuery().eq(BaseDictTypeModel::getDictType, dictType).one();
            if (Objects.nonNull(data))
                cacheAddByDictType(data);
        }
        return data;
    }

    /**
     * 根据id获取单个结果
     *
     * @param id
     * @return
     */
    @Override
    public BaseDictTypeModel selectOneById(Long id) {
        return super.getById(id);
    }

    /**
     * 修改
     *
     * @param param
     * @return
     */
    @Override
    public boolean updateByParam(BaseDictTypeModel param) {
        BaseDictTypeModel model = this.selectOneById(param.getId());
        param.setDictType(model.getDictType());
        boolean result = super.updateById(param);
        if (result) {
            cacheAddByDictType(param);
        }
        return result;
    }

    /**
     * 新增
     *
     * @param param
     * @return
     */
    @Override
    public boolean insertByParam(BaseDictTypeModel param) {
        if (super.save(param)) {
            cacheAddByDictType(param);
        }
        return true;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Long> ids) {
        List<BaseDictTypeModel> list = super.listByIds(ids);
        if (CollUtil.isEmpty(list)) {
            return 0;
        }
        super.removeBatchByIds(ids);
        // 删除子表
        List<String> dictTypeList = list.parallelStream().map(BaseDictTypeModel::getDictType).distinct().collect(Collectors.toList());
        dictDataService.lambdaUpdate().in(BaseDictDataModel::getDictType, dictTypeList).remove();
        return 1;
    }

    /**
     * 获取缓存通过类型
     *
     * @param dictType
     */
    @Override
    public BaseDictTypeModel cacheGetByDictType(String dictType) {
        return redisCache.getCacheObject(KEY_TYPE + dictType);
    }

    /**
     * 新增缓存通过类型
     *
     * @param param
     */
    @Override
    public void cacheAddByDictType(final BaseDictTypeModel param) {
        super.cacheAddById(param);
        redisCache.setCacheObject(KEY_TYPE + param.getDictType(), param);
    }


    /**
     * 删除缓存通过类型
     *
     * @param dictType
     */
    @Override
    public void cacheDeleteByDictType(final String dictType) {
        Optional.ofNullable(this.selectOneByType(dictType)).ifPresent(model -> {
            super.cacheDeleteById(model.getId());
            redisCache.deleteObject(KEY_TYPE + dictType);
        });
    }

    /**
     * 刷新缓存
     */
    @Override
    public void flushCache() {
        redisCache.deleteLike(ModelUtil.modelMainKey(BaseDictTypeModel.class) + StringPool.ASTERISK);
    }

}
