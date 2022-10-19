package com.bebas.org.modules.convert.base;

import com.bebas.org.modules.model.base.dto.SysNoticeDTO;
import com.bebas.org.modules.model.base.model.SysNoticeModel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 通知公告表 Convert
 *
 * @author wuHao
 * @date 2022-10-14 15:13:02
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SysNoticeConvert{

    SysNoticeConvert INSTANCE = Mappers.getMapper(SysNoticeConvert.class);

    /**
    * dto 转换 model
    * @param dto
    * @return
    */
    SysNoticeModel convertToModel(SysNoticeDTO dto);

    /**
    * dtoList 转换 modelList
    * @param dto
    * @return
    */
    List<SysNoticeModel> convertToModel(List<SysNoticeDTO> dto);

    /**
    * model 转换 dto
    * @param model
    * @return
    */
    SysNoticeDTO convertToDTO(SysNoticeModel model);

    /**
    * modelList 转换 dtoList
    * @param model
    * @return
    */
    List<SysNoticeDTO> convertToDTO(List<SysNoticeModel> model);

}
