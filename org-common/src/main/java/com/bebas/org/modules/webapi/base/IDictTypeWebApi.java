package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.BaseDictTypeModel;
import com.bebas.org.modules.model.base.vo.LabelOption;

import java.util.List;

/**
 * 字典类型webApi
 * @author wuyuanjun
 * @date 2022/7/12 23:26
 */
public interface IDictTypeWebApi {

    /**
     * 根据字典类型获取
     * @param dictType
     * @return
     */
    BaseDictTypeModel selectOneByType(String dictType);

    /**
     * 根据id获取单个结果
     * @param id
     * @return
     */
    BaseDictTypeModel selectOneById(Long id);

    /**
     * 修改
     * @param param
     * @return
     */
    boolean updateByParam(BaseDictTypeModel param);

    /**
     * 新增
     * @param param
     * @return
     */
    boolean insertByParam(BaseDictTypeModel param);

    /**
     * 删除
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);

    /**
     * 获取缓存通过类型
     * @param dictType
     */
    BaseDictTypeModel cacheGetByDictType(String dictType);

    /**
     * 新增缓存通过类型
     * @param param
     */
    void cacheAddByDictType(final BaseDictTypeModel param);
    /**
     * 删除缓存通过类型
     * @param dictType
     */
    void cacheDeleteByDictType(final String dictType);

    /**
     * 刷新缓存
     */
    void flushCache();

    /**
     * 获取下拉
     * @return
     */
    List<LabelOption<String,String>> optionSelect();
}
