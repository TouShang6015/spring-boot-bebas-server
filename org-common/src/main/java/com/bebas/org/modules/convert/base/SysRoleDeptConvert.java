package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysRoleDeptDTO;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色和部门关联表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysRoleDeptConvert{

    SysRoleDeptConvert INSTANCE = Mappers.getMapper(SysRoleDeptConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysRoleDeptModel convertToModel(SysRoleDeptDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysRoleDeptModel> convertToModel(List<SysRoleDeptDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysRoleDeptDTO convertToDTO(SysRoleDeptModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysRoleDeptDTO> convertToDTO(List<SysRoleDeptModel> model);

}
