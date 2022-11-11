package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysUserPostDTO;
import com.bebas.org.modules.model.base.model.SysUserPostModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户与岗位关联表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserPostConvert {

    SysUserPostConvert INSTANCE = Mappers.getMapper(SysUserPostConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysUserPostModel convertToModel(SysUserPostDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysUserPostModel> convertToModel(List<SysUserPostDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysUserPostDTO convertToDTO(SysUserPostModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysUserPostDTO> convertToDTO(List<SysUserPostModel> model);

}
