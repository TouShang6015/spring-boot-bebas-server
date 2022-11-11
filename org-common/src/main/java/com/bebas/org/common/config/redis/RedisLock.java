package com.bebas.org.common.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于redisson的分布式锁
 *
 * @author hh
 */
@Slf4j
@Component
public class RedisLock {

    // 锁前缀，方便模糊查询
    public static final String PREFIX = "lock_";
    // 过期时间
    public static final long LOCK_TIMEOUT = 10;
    @Autowired
    private RedissonClient redissonClient;

    // 加锁
    public Boolean lock(String lockName) {
        try {
            // 获取锁
            RLock lock = redissonClient.getLock(PREFIX + lockName);
            // 自动释放，防止死锁
            lock.lock(LOCK_TIMEOUT, TimeUnit.SECONDS);
            log.info("========== 加锁成功： lock [{}] =========", PREFIX + lockName);
            // 加锁成功
            return true;
        } catch (Exception e) {
            log.error("========== 加锁失败： lock [{}] Exception:", PREFIX + lockName, e);
            return false;
        }
    }

    // 加锁
    public Boolean tryLock(String lockName) {
        // 获取锁
        RLock lock = redissonClient.getLock(PREFIX + lockName);
        // 防止死锁
        return lock.tryLock();
    }

    // 释放锁
    public Boolean unlock(String lockName) {
        try {
            // 获取锁
            RLock lock = redissonClient.getLock(PREFIX + lockName);
            // 释放锁
            lock.unlock();
            log.info("========== 释放锁成功： lock [{}] =========线程======={}", PREFIX + lockName, Thread.currentThread().getName());
            // 释放锁成功
            return true;
        } catch (Exception e) {
            log.error("========== 释放锁失败： lock [{}]=====线程===={} Exception:", PREFIX + lockName, Thread.currentThread().getName(), e);
            return false;
        }
    }

}
