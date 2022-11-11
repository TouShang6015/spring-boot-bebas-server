package com.bebas.module.base.web.controller.monitor;

import com.bebas.module.base.web.service.ISysOperLogService;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.base.model.SysOperLogModel;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志记录 控制器
 *
 * @author WuHao
 * @date 2022-06-22 22:35:41
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysoperlog")
@Api(value = "SysOperLogModel", tags = "操作日志记录- 控制器")
public class SysOperLogController extends BaseController<ISysOperLogService, SysOperLogModel> {

    @DeleteMapping("/clean")
    public Result cleanOperlog() {
        service.cleanOperlog();
        return Result.success();
    }

}
