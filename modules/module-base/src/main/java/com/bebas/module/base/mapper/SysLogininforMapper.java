package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysLogininforModel;
import org.apache.ibatis.annotations.Delete;

/**
 * 系统访问记录 持久层接口
 *
 * @author WuHao
 * @date 2022-05-25 08:51:34
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininforModel> {

    @Delete("TRUNCATE sys_logininfor")
    void clean();
}
