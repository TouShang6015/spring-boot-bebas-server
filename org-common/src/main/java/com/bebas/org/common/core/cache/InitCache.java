package com.bebas.org.common.core.cache;

/**
 * 初始化缓存接口
 *
 * @author WuHao
 * @date 2022/5/21 21:08
 * @since 实现该接口会在启动时调用
 */
public interface InitCache {

    /**
     * 初始化缓存
     */
    void runInitCache();

}
