package com.bebas.org.modules.webapi.base;

/**
 * @author WuHao
 * @date 2022/6/5 11:11
 */
public interface ISysUserTokenWebApi {

    /**
     * 根据tokenKey删除
     *
     * @param tokenKey
     * @return
     */
    boolean removeByTokenKey(String tokenKey);

}
