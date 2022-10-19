package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.BaseResourceConfigModel;

/**
 * 系统参数配置表 webapi
 * @author WuHao
 * @date 2022/5/21 13:44
 */
public interface ResourceConfigWebApi {

    /**
     * 根据configKey查询单个信息
     * @param configKey
     * @return
     */
    BaseResourceConfigModel queryByConfigKey(String configKey);

    /**
     * 根据configKey获取configValue
     * @param configKey
     * @return
     */
    String queryValueByConfigKey(String configKey);

    /**
     * 根据configKey获取configValue
     * @param configKey
     * @param tClass
     * @param cache 是否使用缓存
     * @param <T>
     * @return
     */
    <T> T queryValueByConfigKey(String configKey,Class<T> tClass,Boolean cache);

    default <T> T queryValueByConfigKey(String configKey,Class<T> tClass){
        return queryValueByConfigKey(configKey, tClass,true);
    }

    /**
     * 根据configKey编辑configValue
     * @param param
     * @return
     */
    boolean editByConfigKey(BaseResourceConfigModel param);

}
