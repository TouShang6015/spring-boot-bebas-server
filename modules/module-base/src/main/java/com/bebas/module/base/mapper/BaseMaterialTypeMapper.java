package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.BaseMaterialTypeModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 素材分类 持久层接口
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 */
public interface BaseMaterialTypeMapper extends BaseMapper<BaseMaterialTypeModel> {

    @Select("select * from base_material_type where find_in_set(#{id}, ancestors)")
    List<BaseMaterialTypeModel> selectChildrenById(Long id);
}
