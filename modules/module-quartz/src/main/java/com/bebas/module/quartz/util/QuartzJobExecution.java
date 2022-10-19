package com.bebas.module.quartz.util;

import com.bebas.org.modules.model.quartz.model.SysJobModel;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author ruoyi
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJobModel sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
