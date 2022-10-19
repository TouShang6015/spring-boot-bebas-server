package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysUserTokenDTO;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 登录人token信息 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysUserTokenConvert{

    SysUserTokenConvert INSTANCE = Mappers.getMapper(SysUserTokenConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysUserTokenModel convertToModel(SysUserTokenDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysUserTokenModel> convertToModel(List<SysUserTokenDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysUserTokenDTO convertToDTO(SysUserTokenModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysUserTokenDTO> convertToDTO(List<SysUserTokenModel> model);

}
