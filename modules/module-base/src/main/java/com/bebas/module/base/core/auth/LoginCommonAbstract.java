package com.bebas.module.base.core.auth;

import com.bebas.org.common.constants.ResourceConfigConstant;
import com.bebas.org.modules.model.base.vo.baseResource.ResourceMainVO;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;

/**
 * 登陆抽象接口
 * @author WuHao
 * @date 2022/5/31 19:06
 */
public abstract class LoginCommonAbstract<LoginUser,LoginResponse> implements LoginCommonService<LoginUser,LoginResponse> {

    protected ResourceMainVO mainVO;

    protected ResourceConfigWebApi resourceConfigWebApi;

    public LoginCommonAbstract(ResourceConfigWebApi resourceConfigWebApi) {
        this.resourceConfigWebApi = resourceConfigWebApi;
    }

    protected void flushConfig(){
        this.mainVO = resourceConfigWebApi.queryValueByConfigKey(ResourceConfigConstant.MAIN_KEY, ResourceMainVO.class,false);
    }

    /**
     * 登陆操作，获取实体
     * @param loginUser
     * @return
     */
    public abstract LoginResponse doLogin(LoginUser loginUser);

    /**
     * 获取token
     * @param loginUser
     * @return
     */
    public abstract String getToken(LoginResponse loginUser);

    /**
     * 获取最大登陆人数
     * @return
     */
    protected abstract Integer getMaxLogin();

}
