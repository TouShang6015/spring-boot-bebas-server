package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysRoleMenuDTO;
import com.bebas.org.modules.model.base.model.SysRoleMenuModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色和菜单关联表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysRoleMenuConvert{

    SysRoleMenuConvert INSTANCE = Mappers.getMapper(SysRoleMenuConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysRoleMenuModel convertToModel(SysRoleMenuDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysRoleMenuModel> convertToModel(List<SysRoleMenuDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysRoleMenuDTO convertToDTO(SysRoleMenuModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysRoleMenuDTO> convertToDTO(List<SysRoleMenuModel> model);

}
