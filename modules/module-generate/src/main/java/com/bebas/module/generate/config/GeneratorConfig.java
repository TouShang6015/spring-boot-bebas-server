package com.bebas.module.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhao
 * @date 2022/10/8 18:12
 */
@Data
@Component
// 指定配置文件
@PropertySource("classpath:properties/generator.properties")
@ConfigurationProperties(prefix = "data")
public class GeneratorConfig {

    private Map<String, String> type = new HashMap<>();

}
