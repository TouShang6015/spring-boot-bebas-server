package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.bebas.org.modules.model.base.vo.RegisterBodyVo;
import com.bebas.org.modules.model.base.vo.excel.SysUserExcelVo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wyj
 * @date 2022/10/13 14:18
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    @Mappings({
            @Mapping(source = "dto.dept.deptName",target = "deptName")
    })
    SysUserExcelVo convertToExcel(SysUserDTO dto);

    /**
     * 注册实体转换userDto
     * @param register
     * @return
     */
    SysUserDTO convertToRegister(RegisterBodyVo register);

    /**
     * dto 转换 model
     * @param dto
     * @return
     */
    SysUserModel convertToModel(SysUserDTO dto);

    /**
     * dtoList 转换 modelList
     * @param dto
     * @return
     */
    List<SysUserModel> convertToModel(List<SysUserDTO> dto);

    SysUserDTO convertToDTO(SysUserModel model);

    List<SysUserDTO> convertToDTO(List<SysUserModel> model);

}
