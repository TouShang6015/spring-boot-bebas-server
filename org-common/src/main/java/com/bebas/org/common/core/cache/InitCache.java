package com.bebas.org.common.core.cache;

/**
 * 初始化缓存接口
 *
 * @since 实现该接口会在启动时调用
 * @author WuHao
 * @date 2022/5/21 21:08
 */
public interface InitCache {

    /**
     * 初始化缓存
     */
    void runInitCache();

}
