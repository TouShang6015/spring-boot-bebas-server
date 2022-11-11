package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysRoleDTO;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色信息表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysRoleModel convertToModel(SysRoleDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysRoleModel> convertToModel(List<SysRoleDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysRoleDTO convertToDTO(SysRoleModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysRoleDTO> convertToDTO(List<SysRoleModel> model);

}
