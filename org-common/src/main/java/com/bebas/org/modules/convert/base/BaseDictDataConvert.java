package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.BaseDictDataDTO;
import com.bebas.org.modules.model.base.model.BaseDictDataModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典数据表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface BaseDictDataConvert {

    BaseDictDataConvert INSTANCE = Mappers.getMapper(BaseDictDataConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    BaseDictDataModel convertToModel(BaseDictDataDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<BaseDictDataModel> convertToModel(List<BaseDictDataDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    BaseDictDataDTO convertToDTO(BaseDictDataModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<BaseDictDataDTO> convertToDTO(List<BaseDictDataModel> model);

}
