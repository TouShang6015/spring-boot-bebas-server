package com.bebas.module.base.web.service.impl;

import com.alibaba.fastjson2.JSON;
import com.bebas.module.base.mapper.SysOperLogMapper;
import com.bebas.module.base.web.service.ISysOperLogService;
import com.bebas.org.common.constants.ChannelConstant;
import com.bebas.org.framework.asyncMessage.annotation.MessageListener;
import com.bebas.org.modules.model.base.model.SysOperLogModel;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * 操作日志记录 业务实现类
 *
 * @author WuHao
 * @date 2022-06-22 22:35:41
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLogModel> implements ISysOperLogService {

    @MessageListener(ChannelConstant.Log.HANDEL)
    @Override
    public void listenerInsertOperLog(byte[] data) {
        SysOperLogModel sysOperLogModel = JSON.parseObject(new String(data, Charset.defaultCharset()), SysOperLogModel.class);
        if (super.save(sysOperLogModel)) {
            log.info("[操作日志记录] 新增操作日志。");
        } else {
            log.error("[操作日志记录] 新增日志失败。");
        }
    }

    /**
     * 清空日志
     */
    @Override
    public void cleanOperlog() {
        baseMapper.cleanOperlog();
    }
}
