package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.BaseMaterialTypeDTO;
import com.bebas.org.modules.model.base.model.BaseMaterialTypeModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 素材分类 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface BaseMaterialTypeConvert{

    BaseMaterialTypeConvert INSTANCE = Mappers.getMapper(BaseMaterialTypeConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    BaseMaterialTypeModel convertToModel(BaseMaterialTypeDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<BaseMaterialTypeModel> convertToModel(List<BaseMaterialTypeDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    BaseMaterialTypeDTO convertToDTO(BaseMaterialTypeModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<BaseMaterialTypeDTO> convertToDTO(List<BaseMaterialTypeModel> model);

}
