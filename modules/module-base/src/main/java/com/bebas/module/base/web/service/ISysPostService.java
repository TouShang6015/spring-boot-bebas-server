package com.bebas.module.base.web.service;

import com.org.bebasWh.mapper.service.IService;
import com.bebas.org.modules.model.base.model.SysPostModel;

/**
 * 岗位信息表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysPostService extends IService<SysPostModel> {

    /**
     * 校验岗位名称是否唯一
     *
     * @param model 岗位信息
     * @return 结果
     */
    boolean checkPostNameUnique(SysPostModel model);

    /**
     * 校验岗位编码是否唯一
     *
     * @param model 岗位信息
     * @return 结果
     */
    boolean checkPostCodeUnique(SysPostModel model);
}
