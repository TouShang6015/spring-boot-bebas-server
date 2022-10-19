package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.BaseMaterialInfoDTO;
import com.bebas.org.modules.model.base.model.BaseMaterialInfoModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 素材管理 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface BaseMaterialInfoConvert{

    BaseMaterialInfoConvert INSTANCE = Mappers.getMapper(BaseMaterialInfoConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    BaseMaterialInfoModel convertToModel(BaseMaterialInfoDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<BaseMaterialInfoModel> convertToModel(List<BaseMaterialInfoDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    BaseMaterialInfoDTO convertToDTO(BaseMaterialInfoModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<BaseMaterialInfoDTO> convertToDTO(List<BaseMaterialInfoModel> model);

}
