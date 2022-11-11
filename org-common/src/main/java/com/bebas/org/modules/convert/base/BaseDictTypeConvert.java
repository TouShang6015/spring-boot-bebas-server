package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.BaseDictTypeDTO;
import com.bebas.org.modules.model.base.model.BaseDictTypeModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface BaseDictTypeConvert {

    BaseDictTypeConvert INSTANCE = Mappers.getMapper(BaseDictTypeConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    BaseDictTypeModel convertToModel(BaseDictTypeDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<BaseDictTypeModel> convertToModel(List<BaseDictTypeDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    BaseDictTypeDTO convertToDTO(BaseDictTypeModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<BaseDictTypeDTO> convertToDTO(List<BaseDictTypeModel> model);

}
