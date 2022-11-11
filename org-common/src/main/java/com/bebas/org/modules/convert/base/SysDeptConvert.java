package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysDeptConvert {

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysDeptModel convertToModel(SysDeptDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysDeptModel> convertToModel(List<SysDeptDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysDeptDTO convertToDTO(SysDeptModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysDeptDTO> convertToDTO(List<SysDeptModel> model);

}
