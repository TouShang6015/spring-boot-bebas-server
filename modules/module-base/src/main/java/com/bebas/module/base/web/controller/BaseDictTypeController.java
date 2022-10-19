package com.bebas.module.base.web.controller;

import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.result.Result;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.model.base.model.BaseDictTypeModel;
import com.bebas.module.base.web.service.IBaseDictTypeService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典类型表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/basedicttype")
@Api(value = "BaseDictTypeModel",tags = "字典类型")
public class BaseDictTypeController extends BaseController<IBaseDictTypeService,BaseDictTypeModel> {

    @Resource
    public void setService(IBaseDictTypeService service) {
        super.service = service;
    }

    @ApiOperation(value = "获取下拉", notes = "获取下拉", httpMethod = "GET", response = Result.class)
    @ApiOperationSupport(order = 11)
    @GetMapping("/optionSelect")
    public Result optionSelect(){
        return Result.success(service.optionSelect());
    }

    @ApiOperation(value = "刷新缓存", httpMethod = "GET", response = Result.class)
    @ApiOperationSupport(order = 12)
    @DeleteMapping("/flushCache")
    public Result flushCache(){
        service.flushCache();
        return Result.success();
    }

    @Override
    protected Result baseQueryById(@PathVariable("id") Long id) {
        return Result.success(service.selectOneById(id));
    }

    @Log(title = "字典类型新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        BaseDictTypeModel param = MapperUtil.convert(m, BaseDictTypeModel.class);
        return Result.successBoolean(service.insertByParam(param));
    }

    @Log(title = "字典类型编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        BaseDictTypeModel param = MapperUtil.convert(m, BaseDictTypeModel.class);
        return Result.successBoolean(service.updateByParam(param));
    }

    @Log(title = "字典类型删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable String ids) {
        List<Long> idsList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toList()).stream().map(Long::valueOf).collect(Collectors.toList());
        return Result.success(service.deleteByIds(idsList));
    }
}
