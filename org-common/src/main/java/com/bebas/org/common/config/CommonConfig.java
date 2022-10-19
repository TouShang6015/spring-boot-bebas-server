package com.bebas.org.common.config;

import com.bebas.org.common.security.filter.AccessDecisionManagerImpl;
import com.bebas.org.common.security.filter.PermissionSecurityFilter;
import com.bebas.org.common.security.filter.SecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wuhao
 * @date 2022/9/6 11:29
 */
@Configuration
@EnableTransactionManagement        // 开启事务
public class CommonConfig {

    @Bean
    public PermissionSecurityFilter dynamicSecurityFilter(){
        return new PermissionSecurityFilter();
    }

    @Bean
    public SecurityMetadataSource dynamicSecurityMetadataSource() {
        return new SecurityMetadataSource();
    }

    @Bean
    public AccessDecisionManagerImpl dynamicAccessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

}
