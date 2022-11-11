package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysUserRoleDTO;
import com.bebas.org.modules.model.base.model.SysUserRoleModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户和角色关联表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserRoleConvert {

    SysUserRoleConvert INSTANCE = Mappers.getMapper(SysUserRoleConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysUserRoleModel convertToModel(SysUserRoleDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysUserRoleModel> convertToModel(List<SysUserRoleDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysUserRoleDTO convertToDTO(SysUserRoleModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysUserRoleDTO> convertToDTO(List<SysUserRoleModel> model);

}
