package com.bebas.module.quartz.web.service;

import com.org.bebasWh.mapper.service.IService;
import com.bebas.org.modules.model.quartz.model.SysJobModel;
import org.quartz.SchedulerException;

/**
 * 定时任务调度表 业务接口
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
public interface ISysJobService extends IService<SysJobModel> {

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    boolean checkCronExpressionIsValid(String cronExpression);

    /**
     * 任务调度状态修改
     * @param newJob
     * @return
     */
    Boolean changeStatus(SysJobModel newJob);

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return 结果
     */
    Boolean pauseJob(SysJobModel job) throws SchedulerException;

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return 结果
     */
    Boolean resumeJob(SysJobModel job) throws SchedulerException;

    /**
     * 立即执行任务
     * @param job
     */
    void run(SysJobModel job);
}
