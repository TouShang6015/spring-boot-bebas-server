package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysOperLogModel;
import org.apache.ibatis.annotations.Delete;

/**
 * 操作日志记录 持久层接口
 *
 * @author WuHao
 * @date 2022-06-22 22:35:41
 */
public interface SysOperLogMapper extends BaseMapper<SysOperLogModel> {

    /**
     * 清空所有日志
     */
    @Delete("TRUNCATE sys_oper_log")
    void cleanOperlog();
}
