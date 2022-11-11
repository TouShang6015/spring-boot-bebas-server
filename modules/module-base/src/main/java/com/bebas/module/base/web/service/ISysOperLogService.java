package com.bebas.module.base.web.service;

import com.bebas.org.modules.model.base.model.SysOperLogModel;
import com.org.bebasWh.mapper.service.IService;

/**
 * 操作日志记录 业务接口
 *
 * @author WuHao
 * @date 2022-06-22 22:35:41
 */
public interface ISysOperLogService extends IService<SysOperLogModel> {

    void listenerInsertOperLog(byte[] data);

    /**
     * 清空日志
     */
    void cleanOperlog();
}
