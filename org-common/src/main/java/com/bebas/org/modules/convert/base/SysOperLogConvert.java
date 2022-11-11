package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysOperLogDTO;
import com.bebas.org.modules.model.base.model.SysOperLogModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 操作日志记录 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysOperLogConvert {

    SysOperLogConvert INSTANCE = Mappers.getMapper(SysOperLogConvert.class);

    /**
     * dto 转换 model
     *
     * @param dto
     * @return
     */
    SysOperLogModel convertToModel(SysOperLogDTO dto);

    /**
     * dtoList 转换 modelList
     *
     * @param dto
     * @return
     */
    List<SysOperLogModel> convertToModel(List<SysOperLogDTO> dto);

    /**
     * model 转换 dto
     *
     * @param model
     * @return
     */
    SysOperLogDTO convertToDTO(SysOperLogModel model);

    /**
     * modelList 转换 dtoList
     *
     * @param model
     * @return
     */
    List<SysOperLogDTO> convertToDTO(List<SysOperLogModel> model);

}
