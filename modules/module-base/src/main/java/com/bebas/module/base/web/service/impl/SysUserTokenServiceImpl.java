package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.bebas.module.base.mapper.SysUserTokenMapper;
import com.bebas.module.base.web.service.*;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import com.bebas.org.modules.webapi.base.ISysUserTokenWebApi;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录人token信息 业务实现类
 *
 * @author WuHao
 * @date 2022-06-01 11:19:47
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper,SysUserTokenModel> implements ISysUserTokenService, ISysUserTokenWebApi {

    @Resource
    @Override
    protected void setMapper(SysUserTokenMapper mapper) {
        super.mapper = mapper;
    }

    /**
     * 根据tokenKey删除
     * @param tokenKey
     * @return
     */
    @Override
    public boolean removeByTokenKey(String tokenKey) {
        List<SysUserTokenModel> list = this.listByParam(SysUserTokenModel.builder().token(tokenKey).build());
        if (CollUtil.isEmpty(list))
            return true;
        return this.removeById(list.get(NumberUtils.INTEGER_ZERO).getId());
    }

}
