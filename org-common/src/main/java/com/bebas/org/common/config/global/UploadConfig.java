package com.bebas.org.common.config.global;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 文件上传参数实体
 *
 * @author wuhao
 * @date 2022/5/7 21:13
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "project.upload")
public class UploadConfig {

    private static Integer maxUploadSize;

    private static Integer maxFileNameLength;

    public static Integer getMaxUploadSize() {
        return maxUploadSize;
    }

    public void setMaxUploadSize(Integer maxUploadSize) {
        UploadConfig.maxUploadSize = maxUploadSize;
    }

    public static Integer getMaxFileNameLength() {
        return maxFileNameLength;
    }

    public void setMaxFileNameLength(Integer maxFileNameLength) {
        UploadConfig.maxFileNameLength = maxFileNameLength;
    }
}
