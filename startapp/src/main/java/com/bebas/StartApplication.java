package com.bebas;

import com.org.bebasWh.utils.logs.LogUtil;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

/**
 * @author wuhao
 * @date 2022/5/7 21:03
 */
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        LogUtil.consoleInfo(LoggerFactory.getLogger(StartApplication.class), "- 关闭druid日志警告", () -> System.setProperty("druid.mysql.usePingMethod", "false"));
        SpringApplication.run(StartApplication.class, args);
    }

    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }


}
