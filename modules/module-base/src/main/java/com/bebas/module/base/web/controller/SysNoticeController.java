package com.bebas.module.base.web.controller;

import com.bebas.org.modules.model.base.model.SysNoticeModel;
import com.bebas.module.base.web.service.ISysNoticeService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通知公告表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysnotice")
@Api(value = "SysNoticeModel",tags = "通知公告")
public class SysNoticeController extends BaseController<ISysNoticeService,SysNoticeModel> {

    @Resource
    @Override
    public void setService(ISysNoticeService service) {
        super.service = service;
    }

}
