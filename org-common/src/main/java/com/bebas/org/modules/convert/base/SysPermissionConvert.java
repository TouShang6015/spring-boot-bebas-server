package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.bebas.org.modules.model.base.model.SysPermissionModel;
import com.bebas.org.modules.model.base.vo.permission.RouteInfo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 权限管理 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysPermissionConvert{

    SysPermissionConvert INSTANCE = Mappers.getMapper(SysPermissionConvert.class);

    /**
     * 转换routeInfo
     * @param modelList
     * @return
     */
    List<RouteInfo> convertToRouteInfo(List<SysPermissionModel> modelList);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysPermissionModel convertToModel(SysPermissionDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysPermissionModel> convertToModel(List<SysPermissionDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysPermissionDTO convertToDTO(SysPermissionModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysPermissionDTO> convertToDTO(List<SysPermissionModel> model);

}
