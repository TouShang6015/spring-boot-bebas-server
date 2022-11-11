package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.model.SysMenuModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单权限表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysMenuConvert {

    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysMenuModel convertToModel(SysMenuDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysMenuModel> convertToModel(List<SysMenuDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysMenuDTO convertToDTO(SysMenuModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysMenuDTO> convertToDTO(List<SysMenuModel> model);

}
