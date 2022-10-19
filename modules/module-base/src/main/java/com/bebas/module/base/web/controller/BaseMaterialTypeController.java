package com.bebas.module.base.web.controller;

import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.result.Result;
import com.bebas.module.base.web.service.IBaseMaterialInfoService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.utils.tree.TreeService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.convert.base.BaseMaterialTypeConvert;
import com.bebas.org.modules.model.base.dto.BaseMaterialTypeDTO;
import com.bebas.org.modules.model.base.model.BaseMaterialInfoModel;
import com.bebas.org.modules.model.base.model.BaseMaterialTypeModel;
import com.bebas.module.base.web.service.IBaseMaterialTypeService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 素材分类 控制器
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/basematerialtype")
@Api(value = "BaseMaterialTypeModel",tags = "素材分类")
public class BaseMaterialTypeController extends BaseController<IBaseMaterialTypeService,BaseMaterialTypeModel> {

    @Resource
    public void setService(IBaseMaterialTypeService service) {
        super.service = service;
    }

    @Resource
    private TreeService<BaseMaterialTypeDTO> treeService;

    @Resource
    private IBaseMaterialInfoService baseMaterialInfoService;

    @Override
    protected Result baseQueryById(@PathVariable Long id) {
        Result result = Result.success();
        Optional.ofNullable(service.getById(id)).ifPresent(model -> {
            result.setData(BaseMaterialTypeConvert.INSTANCE.convertToDTO(model));
        });
        return result;
    }

    @Override
    protected Result baseQueryByParam(@RequestBody BaseMaterialTypeModel param) {
        Result result = Result.success();
        Optional.ofNullable(service.listByParam(param)).ifPresent(list -> {
            result.setData(BaseMaterialTypeConvert.INSTANCE.convertToDTO(list));
        });
        return result;
    }

    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.stream(ids.split(StringPool.COMMA)).distinct().collect(Collectors.toList());
        if (baseMaterialInfoService.lambdaQuery().in(BaseMaterialInfoModel::getMaterialTypeId,idList).count() > 0){
            return Result.fail(MessageUtils.message("common.include.no.handle"));
        }
        return super.baseDeleteByIds(ids);
    }


    @ApiOperation(value = "查询列表（排除节点）", httpMethod = "GET", response = Result.class)
    @GetMapping("/list/exclude/{id}")
    public Result excludeChild(@PathVariable Long id) {
        List<BaseMaterialTypeModel> list = OptionalUtil.ofNullList(service.list()).stream()
                .filter(item -> !(item.getId().equals(id) || Arrays.stream(item.getAncestors().split(StringPool.COMMA)).collect(Collectors.toList()).contains(id.toString())))
                .collect(Collectors.toList());
        return Result.success(list);
    }

    @ApiOperation(value = "获取下拉树列表", httpMethod = "GET", response = Result.class)
    @GetMapping("/treeselect")
    public Result treeselect(BaseMaterialTypeModel param) {
        Long parentId = param.getParentId();
        param.setParentId(null);
        List<BaseMaterialTypeModel> list = service.listByParam(param);
        List<BaseMaterialTypeDTO> treeList = this.treeService.convertTree(BaseMaterialTypeConvert.INSTANCE.convertToDTO(list));
        if (Objects.nonNull(parentId) && !parentId.equals(Constants.DEFAULT_PARENT_ID)) {
            treeList = treeService.filterParent(treeList, parentId);
        }
        List<TreeModel> treeModelList = treeList.stream().map(TreeModel::new).collect(Collectors.toList());
        return Result.success(treeModelList);
    }

}
