package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.BaseResourceConfigDTO;
import com.bebas.org.modules.model.base.model.BaseResourceConfigModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统参数配置表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface BaseResourceConfigConvert{

    BaseResourceConfigConvert INSTANCE = Mappers.getMapper(BaseResourceConfigConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    BaseResourceConfigModel convertToModel(BaseResourceConfigDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<BaseResourceConfigModel> convertToModel(List<BaseResourceConfigDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    BaseResourceConfigDTO convertToDTO(BaseResourceConfigModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<BaseResourceConfigDTO> convertToDTO(List<BaseResourceConfigModel> model);

}
