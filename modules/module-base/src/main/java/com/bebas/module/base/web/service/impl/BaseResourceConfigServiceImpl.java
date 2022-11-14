package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bebas.module.base.mapper.BaseResourceConfigMapper;
import com.bebas.module.base.web.service.IBaseResourceConfigService;
import com.bebas.org.common.constants.ResourceConfigConstant;
import com.bebas.org.common.core.cache.InitCache;
import com.bebas.org.common.core.cache.SinglePool;
import com.bebas.org.modules.model.base.model.BaseResourceConfigModel;
import com.bebas.org.modules.model.base.vo.baseResource.ResourceMainVO;
import com.org.bebasWh.core.function.OpenRunnable;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.mapper.utils.ModelUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统参数配置表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-21 09:02:14
 */
@Service
public class BaseResourceConfigServiceImpl extends ServiceImpl<BaseResourceConfigMapper, BaseResourceConfigModel> implements IBaseResourceConfigService, InitCache, SinglePool {

    private final String CONFIG_KEY = ModelUtil.modelMainKey(BaseResourceConfigModel.class) + "@configKey-";

    @Resource
    private RedisCache redisCache;

    /**
     * 根据configKey查询单个信息
     *
     * @param configKey
     * @return
     */
    @Override
    public BaseResourceConfigModel queryByConfigKey(String configKey) {
        if (StrUtil.isEmpty(configKey))
            return null;
        String key = CONFIG_KEY + configKey;
        BaseResourceConfigModel model = redisCache.getCacheObject(key);
        if (ObjectUtil.isNull(model)) {
            model = lambdaQuery().eq(BaseResourceConfigModel::getConfigKey, configKey).one();
            // 存redis
            BaseResourceConfigModel finalModel = model;
            OpenRunnable.run(ObjectUtil.isNotNull(model), () -> {
                redisCache.setCacheObject(key, finalModel);
            });
        }
        return model;
    }

    /**
     * 根据configKey获取configValue
     *
     * @param configKey
     * @return
     */
    @Override
    public String queryValueByConfigKey(String configKey) {
        BaseResourceConfigModel resourceConfigModel = this.queryByConfigKey(configKey);
        if (ObjectUtil.isNull(resourceConfigModel))
            return null;
        this.flushSingleCache();
        return resourceConfigModel.getResourceValue();
    }

    @Override
    public <T> T queryValueByConfigKey(String configKey, Class<T> tClass, Boolean cache) {
        String resourceValue;
        if (cache) {
            resourceValue = this.queryValueByConfigKey(configKey);
        } else {
            resourceValue = this.lambdaQuery().eq(BaseResourceConfigModel::getConfigKey, configKey).select(BaseResourceConfigModel::getResourceValue).one().getResourceValue();
        }
        if (StrUtil.isEmpty(resourceValue))
            return null;
        return JSONObject.parseObject(resourceValue, tClass);
    }

    /**
     * 根据configKey编辑configValue
     *
     * @param param
     * @return
     */
    @Override
    public boolean editByConfigKey(BaseResourceConfigModel param) {
        String configKey = param.getConfigKey();
        String resourceValue = param.getResourceValue();
        if (StrUtil.isEmpty(configKey))
            return false;
        String key = CONFIG_KEY + configKey;
        BaseResourceConfigModel model = this.queryByConfigKey(configKey);
        if (ObjectUtil.isNull(model)) {
            return false;
        }
        model.setResourceValue(resourceValue);
        if (this.updateById(model)) {
            // 设置redis
            redisCache.deleteObject(key);
        }
        return true;
    }

    /**
     * 初始化缓存
     */
    @Override
    public void runInitCache() {
        List<BaseResourceConfigModel> list = this.list();
        if (CollUtil.isEmpty(list))
            return;
        // 清空缓存
        List<String> redisConfigKeyList = list.stream().map(x -> CONFIG_KEY + x.getConfigKey()).distinct().collect(Collectors.toList());
        redisCache.deleteObject(redisConfigKeyList);
        // 刷新缓存
        list.forEach(item -> {
            redisCache.setCacheObject(CONFIG_KEY + item.getConfigKey(), item);
        });
    }

    /**
     * 刷新缓存
     */
    @Override
    public void flushSingleCache() {
        ResourceMainVO vo = Singleton.get(ResourceMainVO.class);
        ResourceMainVO resourceMainVO = queryValueByConfigKey(ResourceConfigConstant.MAIN_KEY, ResourceMainVO.class, false);
        if (vo == null){
            vo = resourceMainVO;
            Singleton.put(vo);
        }
        BeanUtils.copyProperties(resourceMainVO,vo);
    }
}
