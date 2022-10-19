package com.bebas.module.base.web.controller;

import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.result.Result;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.model.base.model.BaseDictDataModel;
import com.bebas.module.base.web.service.IBaseDictDataService;
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
 * 字典数据表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/basedictdata")
@Api(value = "BaseDictDataModel",tags = "字典数据")
public class BaseDictDataController extends BaseController<IBaseDictDataService,BaseDictDataModel> {

    @Resource
    public void setService(IBaseDictDataService service) {
        super.service = service;
    }

    @ApiOperation(value = "获取下拉", notes = "获取下拉", httpMethod = "GET", response = Result.class)
    @ApiOperationSupport(order = 11)
    @GetMapping("/optionSelect/{dictType}")
    public Result optionSelect(@PathVariable("dictType") String dictType){
        return Result.success(service.optionSelect(dictType));
    }

    @ApiOperation(value = "通过字典类型获取列表", notes = "通过字典类型获取列表", httpMethod = "GET", response = Result.class)
    @ApiOperationSupport(order = 12)
    @GetMapping("/getListByDictType/{dictType}")
    public Result getListByDictType(@PathVariable("dictType") String dictType){
        return Result.success(service.selectListByDictType(dictType));
    }

    @Override
    protected Result baseQueryById(@PathVariable("id") Long id) {
        return Result.success(service.selectOneById(id));
    }

    @Log(title = "字典数据新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        BaseDictDataModel param = MapperUtil.convert(m, BaseDictDataModel.class);
        return Result.successBoolean(service.insertByParam(param));
    }

    @Log(title = "字典数据编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        BaseDictDataModel param = MapperUtil.convert(m, BaseDictDataModel.class);
        return Result.successBoolean(service.updateByParam(param));
    }

    @Log(title = "字典数据删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<Long> idsList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toList()).stream().map(Long::valueOf).collect(Collectors.toList());
        return Result.success(service.deleteByIds(idsList));
    }

}
