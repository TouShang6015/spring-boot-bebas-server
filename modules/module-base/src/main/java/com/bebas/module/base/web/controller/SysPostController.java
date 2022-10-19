package com.bebas.module.base.web.controller;

import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.result.Result;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.module.base.web.service.ISysPostService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 岗位信息表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/syspost")
@Api(value = "SysPostModel", tags = "岗位信息")
public class SysPostController extends BaseController<ISysPostService, SysPostModel> {

    @Resource
    public void setService(ISysPostService service) {
        super.service = service;
    }

    @Log(title = "岗位新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysPostModel model = MapperUtil.convert(m, SysPostModel.class);
        if (!service.checkPostNameUnique(model)) {
            return Result.fail(MessageUtils.message("business.base.post.name.unique"));
        } else if (!service.checkPostCodeUnique(model)) {
            return Result.fail(MessageUtils.message("business.base.post.code.unique"));
        }
        return super.baseAdd(model);
    }

    @Log(title = "岗位编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysPostModel model = MapperUtil.convert(m, SysPostModel.class);
        if (!service.checkPostNameUnique(model)) {
            return Result.fail(MessageUtils.message("business.base.post.name.unique"));
        } else if (!service.checkPostCodeUnique(model)) {
            return Result.fail(MessageUtils.message("business.base.post.code.unique"));
        }
        return super.baseEdit(model);
    }

    @Log(title = "岗位删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        return super.baseDeleteByIds(ids);
    }
}
