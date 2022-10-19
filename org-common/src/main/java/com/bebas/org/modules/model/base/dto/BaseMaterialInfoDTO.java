package com.bebas.org.modules.model.base.dto;

import com.bebas.org.modules.model.base.model.BaseMaterialInfoModel;
import lombok.*;

import java.util.List;

/**
 * 素材管理 Dto
 *
 * @author WuHao
 * @company Wuhao
 * @date 2022-09-09 10:14:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMaterialInfoDTO extends BaseMaterialInfoModel {

    private List<String> materialImageList;

}
