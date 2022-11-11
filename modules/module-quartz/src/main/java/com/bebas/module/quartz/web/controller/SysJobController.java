package com.bebas.module.quartz.web.controller;

import com.bebas.module.quartz.constants.QuartzConstant;
import com.bebas.module.quartz.util.CronUtils;
import com.bebas.module.quartz.util.ScheduleUtils;
import com.bebas.module.quartz.web.service.ISysJobService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.model.quartz.model.SysJobModel;
import com.org.bebasWh.utils.MapperUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务调度表 控制器
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.QUARTZ + "/sysjob")
@Api(value = "SysJobModel", tags = "定时任务调度表")
public class SysJobController extends BaseController<ISysJobService, SysJobModel> {

    @Log(title = "新增")
    @Override
    protected <DTO> Result baseAdd(@RequestBody DTO m) {
        SysJobModel job = MapperUtil.convert(m, SysJobModel.class);
        if (!CronUtils.isValid(job.getCronExpression())) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), QuartzConstant.LOOKUP_RMI)) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{QuartzConstant.LOOKUP_LDAP, QuartzConstant.LOOKUP_LDAPS})) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), QuartzConstant.JOB_ERROR_STR)) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return Result.fail("新增任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        return Result.successBoolean(service.save(job));
    }

    @Log(title = "编辑")
    @Override
    protected <DTO> Result baseEdit(@RequestBody DTO m) {
        SysJobModel job = MapperUtil.convert(m, SysJobModel.class);
        if (!CronUtils.isValid(job.getCronExpression())) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), QuartzConstant.LOOKUP_RMI)) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{QuartzConstant.LOOKUP_LDAP, QuartzConstant.LOOKUP_LDAPS})) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
        } else if (StringUtils.containsAnyIgnoreCase(job.getInvokeTarget(), QuartzConstant.JOB_ERROR_STR)) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，目标字符串存在违规");
        } else if (!ScheduleUtils.whiteList(job.getInvokeTarget())) {
            return Result.fail("修改任务'" + job.getJobName() + "'失败，目标字符串不在白名单内");
        }
        return Result.successBoolean(service.updateById(job));
    }

    @Log(title = "修改任务状态")
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysJobModel job) throws SchedulerException {
        SysJobModel newJob = service.getById(job.getId());
        newJob.setStatus(job.getStatus());
        return Result.successBoolean(service.changeStatus(newJob));
    }

    @Log(title = "立即执行任务")
    @PutMapping("/run")
    public Result run(@RequestBody SysJobModel job) throws SchedulerException {
        service.run(job);
        return Result.success();
    }

}
