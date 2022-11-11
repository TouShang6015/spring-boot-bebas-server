package com.bebas.module.base.web.service;

import com.bebas.org.modules.model.base.model.SysLogininforModel;
import com.bebas.org.modules.webapi.base.ISysLogininforWebApi;
import com.org.bebasWh.mapper.service.IService;

/**
 * 系统访问记录 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 08:51:34
 */
public interface ISysLogininforService extends IService<SysLogininforModel>, ISysLogininforWebApi {

    void clean();
}
