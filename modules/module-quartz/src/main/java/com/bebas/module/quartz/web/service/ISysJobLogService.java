package com.bebas.module.quartz.web.service;

import com.org.bebasWh.mapper.service.IService;
import com.bebas.org.modules.model.quartz.model.SysJobLogModel;

/**
 * 定时任务调度日志表 业务接口
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
public interface ISysJobLogService extends IService<SysJobLogModel> {

    void cleanJobLog();
}
