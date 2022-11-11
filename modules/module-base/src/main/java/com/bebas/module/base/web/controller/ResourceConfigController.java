package com.bebas.module.base.web.controller;

import com.bebas.module.base.web.service.IBaseResourceConfigService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.model.BaseResourceConfigModel;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 系统参数配置表 控制层
 *
 * @author WuHao
 * @date 2022/5/21 11:18
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/baseresourceconfig")
@Api(value = "BaseResourceConfigModel", tags = "系统参数配置")
public class ResourceConfigController extends BaseController<IBaseResourceConfigService, BaseResourceConfigModel> {

    @ApiOperation(value = "根据configKey获取信息", httpMethod = "GET", response = Result.class)
    @GetMapping("/queryByConfigKey/{configKey}")
    public Result queryByConfigKey(@PathVariable String configKey) {
        BaseResourceConfigModel model = service.queryByConfigKey(configKey);
        return Result.success(model);
    }

    @Log(title = "编辑系统参数配置信息")
    @ApiOperation(value = "根据configKey编辑信息", httpMethod = "PUT", response = Result.class)
    @PutMapping("/editByConfigKey")
    public Result editByConfigKey(@RequestBody BaseResourceConfigModel param) {
        if (!service.editByConfigKey(param))
            return Result.fail();
        return Result.success();
    }

    // =======


    @ApiIgnore
    @Override
    protected Result baseQueryById(Long id) {
        return super.baseQueryById(id);
    }

    @ApiIgnore
    @Override
    protected Result baseQueryPageByParam(BaseResourceConfigModel param) {
        return super.baseQueryPageByParam(param);
    }

    @ApiIgnore
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        return super.baseAdd(m);
    }

    @ApiIgnore
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        return super.baseEdit(m);
    }

    @ApiIgnore
    @Override
    protected Result baseDeleteByIds(String ids) {
        return super.baseDeleteByIds(ids);
    }
}
