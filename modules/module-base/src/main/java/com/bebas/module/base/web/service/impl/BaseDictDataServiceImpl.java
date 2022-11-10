package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bebas.module.base.mapper.BaseDictDataMapper;
import com.bebas.module.base.web.service.IBaseDictDataService;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.modules.model.base.model.BaseDictDataModel;
import com.bebas.org.modules.model.base.vo.LabelOption;
import com.org.bebasWh.constants.RedisConstant;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.mapper.utils.ModelUtil;
import com.org.bebasWh.utils.OptionalUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典数据表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@Service
public class BaseDictDataServiceImpl extends ServiceImpl<BaseDictDataMapper, BaseDictDataModel> implements IBaseDictDataService {

    private final String KEY_LIST = ModelUtil.modelMainKey(BaseDictDataModel.class) + RedisConstant.Keyword.LIST_PARAM;

    private final String KEY_KEYWORD = ModelUtil.modelMainKey(BaseDictDataModel.class) + RedisConstant.Keyword.ID;

    @Resource
    private RedisCache redisCache;

    /**
     * 获取下拉
     *
     * @return
     */
    @Override
    public List<LabelOption<String, String>> optionSelect(String dictType) {
        return OptionalUtil.ofNullList(this.selectListByDictType(dictType)).stream().map(item -> {
            LabelOption<String, String> option = new LabelOption<>();
            option.setValue(item.getDictValue());
            option.setLabel(item.getDictLabel());
            return option;
        }).collect(Collectors.toList());
    }

    /**
     * 通过字典类型跟值获取对象
     *
     * @param dictType
     * @param dictValue
     * @return
     */
    @Override
    public BaseDictDataModel selectOneByTypeAndValue(@NotNull(message = "字典类型不能为空") String dictType, @NotNull(message = "字典值不能为空") String dictValue) {
        List<BaseDictDataModel> list = this.selectListByDictType(dictType);
        if (!CollUtil.isEmpty(list)) {
            return list.parallelStream().filter(item -> item.getDictValue().equals(dictValue)).findFirst().orElse(null);
        }
        return null;
    }

    /**
     * 通过字典类型获取列表
     *
     * @param dictType
     * @return
     */
    @Override
    public List<BaseDictDataModel> selectListByDictType(String dictType) {
        List<BaseDictDataModel> list = redisCache.getCacheList(KEY_LIST + dictType);
        if (CollUtil.isEmpty(list)) {
            list = super.listByParam(BaseDictDataModel.builder().dictType(dictType).build());
            if (!CollUtil.isEmpty(list))
                redisCache.setCacheList(KEY_LIST + dictType, list);
        }
        return list;
    }

    /**
     * 根据id获取单个结果
     *
     * @param id
     * @return
     */
    @Override
    public BaseDictDataModel selectOneById(Long id) {
        return super.getById(id);
    }

    /**
     * 修改
     *
     * @param param
     * @return
     */
    @Override
    public boolean updateByParam(BaseDictDataModel param) {
        boolean result = super.updateById(param);
        if (result) {
            redisCache.deleteObject(KEY_LIST + param.getDictType());
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
    public boolean insertByParam(BaseDictDataModel param) {
        if (super.save(param)) {
            this.flushCache(param.getDictType());
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
        // 删除缓存
        Optional.ofNullable(ids)
                .flatMap(idsList ->
                        Optional.of(idsList.parallelStream().map(this::selectOneById).map(BaseDictDataModel::getDictType).distinct().collect(Collectors.toList()))
                )
                .ifPresent(dictTypeList -> {
                    dictTypeList.parallelStream().forEach(this::flushCache);
                    super.removeBatchByIds(ids);
                });
        return 1;
    }

    /**
     * 刷新缓存
     *
     * @param dictType
     */
    @Override
    public void flushCache(String dictType) {
        redisCache.deleteObject(KEY_LIST + dictType);
    }

    /**
     * 刷新缓存
     */
    @Override
    public void flushCache() {
        redisCache.deleteLike(ModelUtil.modelMainKey(BaseDictDataModel.class) + StringPool.ASTERISK);
    }
}
