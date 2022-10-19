package com.bebas.config;

import com.bebas.org.common.config.global.BaseInfoConfig;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger 文档配置
 *
 * @author WuHao
 * @date 2022/5/12 11:38
 */
@Configuration
@EnableSwagger2
@Profile({"local","test"})        // 只在local test环境下开启 swagger
public class SwaggerConfig {

    /**
     * 是否开启swagger
     */
    @Value("${swagger.enabled}")
    private boolean enabled;

    /**
     * 设置请求的统一前缀
     */
    @Value("${swagger.pathMapping}")
    private String pathMapping;

    /**
     * base模块api
     */
    @Bean
    public Docket createApi_base() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bebas.module.base.web"))
                .paths(PathSelectors.any())
                .build()
                .groupName("基本模块接口-接口文档")
                .pathMapping(pathMapping)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    /**
     * generate模块Api
     */
    @Bean
    public Docket createApi_generate() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bebas.module.generate.web"))
                .paths(PathSelectors.any())
                .build()
                .groupName("generate模块-接口文档")
                .pathMapping(pathMapping)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    /**
     * quartz模块Api
     */
    @Bean
    public Docket createApi_quartz() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bebas.module.quartz.web"))
                .paths(PathSelectors.any())
                .build()
                .groupName("quartz模块-接口文档")
                .pathMapping(pathMapping)
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                ;
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title(BaseInfoConfig.getName() + "接口文档-版本" + BaseInfoConfig.getVersion())
                // 描述
                .description("描述：...")
                // 作者信息
                .contact(new Contact(BaseInfoConfig.getAuthor(), null, null))
                // 版本
                .version("版本号:" + BaseInfoConfig.getVersion())
                .build();
    }


    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<SecurityScheme>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forHttpMethods(o -> o.matches("/.*"))
                        .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

}
