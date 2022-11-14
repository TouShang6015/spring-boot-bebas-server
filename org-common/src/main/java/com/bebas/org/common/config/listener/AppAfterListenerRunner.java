package com.bebas.org.common.config.listener;

import com.bebas.org.common.core.cache.InitCache;
import com.bebas.org.common.core.cache.SinglePool;
import com.org.bebasWh.utils.logs.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author WuHao
 * @date 2022/5/12 10:30
 */
@Component
@Slf4j
public class AppAfterListenerRunner implements CommandLineRunner {

    @Resource
    private List<InitCache> cacheList;
    @Resource
    private List<SinglePool> singlePools;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) {
        // todo 启动后执行
        LogUtil.consoleInfo(log, "- 初始化缓存数据", () -> {
            Optional.ofNullable(cacheList).ifPresent(l -> l.forEach(InitCache::runInitCache));
            Optional.ofNullable(singlePools).ifPresent(l -> l.forEach(SinglePool::flushSingleCache));
        });
    }


}
