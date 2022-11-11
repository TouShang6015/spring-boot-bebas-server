package com.bebas.module.base.web.controller;

import com.bebas.module.base.web.service.IBaseMaterialInfoService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.dto.BaseMaterialInfoDTO;
import com.bebas.org.modules.model.base.model.BaseMaterialInfoModel;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 素材管理 控制器
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/basematerialinfo")
@Api(value = "BaseMaterialInfoModel", tags = "素材管理")
public class BaseMaterialInfoController extends BaseController<IBaseMaterialInfoService, BaseMaterialInfoModel> {

    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        BaseMaterialInfoDTO dto = MapperUtil.convert(m, BaseMaterialInfoDTO.class);
        List<BaseMaterialInfoModel> insertList = dto.getMaterialImageList().stream().map(item -> BaseMaterialInfoModel
                .builder()
                .materialTypeId(dto.getMaterialTypeId())
                .materialName(dto.getMaterialName())
                .url(item)
                .build()
        ).collect(Collectors.toList());
        if (!service.saveBatch(insertList)) {
            return Result.fail();
        }
        return Result.success();
    }
}
