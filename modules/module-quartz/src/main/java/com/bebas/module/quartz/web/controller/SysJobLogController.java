package com.bebas.module.quartz.web.controller;

import com.org.bebasWh.utils.result.Result;
import com.bebas.module.quartz.web.service.ISysJobLogService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.quartz.model.SysJobLogModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 定时任务调度日志表 控制器
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.QUARTZ + "/sysjoblog")
@Api(value = "SysJobLogModel", tags = "定时任务调度日志表")
public class SysJobLogController extends BaseController<ISysJobLogService, SysJobLogModel> {

    @Resource
    public void setService(ISysJobLogService service) {
        super.service = service;
    }

    @DeleteMapping("/clean")
    public Result clean() {
        service.cleanJobLog();
        return Result.success();
    }
}
