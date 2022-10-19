package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysRolePermissionDTO;
import com.bebas.org.modules.model.base.model.SysRolePermissionModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色和权限关联表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysRolePermissionConvert{

    SysRolePermissionConvert INSTANCE = Mappers.getMapper(SysRolePermissionConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysRolePermissionModel convertToModel(SysRolePermissionDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysRolePermissionModel> convertToModel(List<SysRolePermissionDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysRolePermissionDTO convertToDTO(SysRolePermissionModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysRolePermissionDTO> convertToDTO(List<SysRolePermissionModel> model);

}
