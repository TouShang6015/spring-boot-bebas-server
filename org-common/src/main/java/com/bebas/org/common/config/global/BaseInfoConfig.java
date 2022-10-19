package com.bebas.org.common.config.global;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wuhao
 * @date 2022/5/7 21:13
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "project.baseinfo")
public class BaseInfoConfig {

    private static String name;

    private static String version;

    private static boolean demoEnabled;

    private static String author;

    public static String getName() {
        return name;
    }

    public static String getVersion() {
        return version;
    }

    public void setName(String name) {
        BaseInfoConfig.name = name;
    }

    public void setVersion(String version) {
        BaseInfoConfig.version = version;
    }

    public static boolean getDemoEnabled() {
        return demoEnabled;
    }

    public static boolean isDemoEnabled() {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled) {
        BaseInfoConfig.demoEnabled = demoEnabled;
    }

    public static String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        BaseInfoConfig.author = author;
    }
}
