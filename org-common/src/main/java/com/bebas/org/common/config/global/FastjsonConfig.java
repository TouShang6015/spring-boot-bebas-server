package com.bebas.org.common.config.global;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Wuhao
 * @date 2022/9/24 12:10
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "project.fastjson")
public class FastjsonConfig {

    private static String redisSerializerPackage;

    public static String getRedisSerializerPackage() {
        return redisSerializerPackage;
    }

    public void setRedisSerializerPackage(String redisSerializerPackage) {
        FastjsonConfig.redisSerializerPackage = redisSerializerPackage;
    }
}
