package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysPostDTO;
import com.bebas.org.modules.model.base.model.SysPostModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 岗位信息表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysPostConvert {

    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysPostModel convertToModel(SysPostDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysPostModel> convertToModel(List<SysPostDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysPostDTO convertToDTO(SysPostModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysPostDTO> convertToDTO(List<SysPostModel> model);

}
