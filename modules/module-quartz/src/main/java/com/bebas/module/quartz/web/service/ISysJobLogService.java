package com.bebas.module.quartz.web.service;

import com.bebas.org.modules.model.quartz.model.SysJobLogModel;
import com.org.bebasWh.mapper.service.IService;

/**
 * 定时任务调度日志表 业务接口
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
public interface ISysJobLogService extends IService<SysJobLogModel> {

    void cleanJobLog();
}
