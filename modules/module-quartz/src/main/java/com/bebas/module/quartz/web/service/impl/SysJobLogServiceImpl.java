package com.bebas.module.quartz.web.service.impl;

import com.bebas.module.quartz.mapper.SysJobLogMapper;
import com.bebas.org.modules.model.quartz.model.SysJobLogModel;
import com.bebas.module.quartz.web.service.ISysJobLogService;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时任务调度日志表 业务实现类
 *
 * @author WuHao
 * @date 2022-09-06 18:51:31
 */
@Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper,SysJobLogModel> implements ISysJobLogService {

    @Resource
    protected void setMapper(SysJobLogMapper mapper) {
        super.mapper = mapper;
    }

    @Override
    public void cleanJobLog() {
        mapper.cleanJobLog();
    }
}
