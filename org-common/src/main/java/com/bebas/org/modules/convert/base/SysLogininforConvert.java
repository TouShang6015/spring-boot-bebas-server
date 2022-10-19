package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysLogininforDTO;
import com.bebas.org.modules.model.base.model.SysLogininforModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统访问记录 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysLogininforConvert{

    SysLogininforConvert INSTANCE = Mappers.getMapper(SysLogininforConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysLogininforModel convertToModel(SysLogininforDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysLogininforModel> convertToModel(List<SysLogininforDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysLogininforDTO convertToDTO(SysLogininforModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysLogininforDTO> convertToDTO(List<SysLogininforModel> model);

}
