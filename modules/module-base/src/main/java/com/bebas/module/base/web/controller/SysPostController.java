package com.bebas.module.base.web.controller;

import com.bebas.module.base.web.service.ISysPostService;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.dto.SysPostDTO;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bebas.org.common.constants.MessageCode.Post.POST_CODE_UNIQUE;
import static com.bebas.org.common.constants.MessageCode.Post.POST_NAME_UNIQUE;

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

    @Log(title = "岗位新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysPostDTO dto = MapperUtil.convert(m, SysPostDTO.class);
        if (!service.checkPostNameUnique(dto)) {
            return Result.fail(MessageUtils.message(POST_NAME_UNIQUE));
        } else if (!service.checkPostCodeUnique(dto)) {
            return Result.fail(MessageUtils.message(POST_CODE_UNIQUE));
        }
        return super.baseAdd(dto);
    }

    @Log(title = "岗位编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysPostDTO dto = MapperUtil.convert(m, SysPostDTO.class);
        if (!service.checkPostNameUnique(dto)) {
            return Result.fail(MessageUtils.message(POST_NAME_UNIQUE));
        } else if (!service.checkPostCodeUnique(dto)) {
            return Result.fail(MessageUtils.message(POST_CODE_UNIQUE));
        }
        return super.baseEdit(dto);
    }

    @Log(title = "岗位删除")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        return super.baseDeleteByIds(ids);
    }
}
