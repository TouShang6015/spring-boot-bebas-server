package com.bebas.org.common.config.redis;

import com.alibaba.fastjson.parser.ParserConfig;
import com.org.bebasWh.utils.redis.FastJsonRedisSerializer;
import com.org.bebasWh.constants.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;

/**
 * @author WuHao
 * @date 2022/5/13 9:51
 */
@Configuration
@ConditionalOnExpression("${default.redis.enabled:true}")
public class RedisConfig {

    @Autowired
    private Environment environment;
    @Value("${project.fastjson.redisSerializerPackage}")
    private String redisSerializerPackage;

    @Primary
    @Bean(RedisConstant.TemplateKey.REDIS_TEMPLATE)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // value 自定义序列化配置
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        ParserConfig.getGlobalInstance().addAccept(redisSerializerPackage);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        return redisTemplate;
    }

    /**
     * redisson客户端
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(){
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        StringBuilder address = new StringBuilder();
        address.append("redis://").append(environment.getProperty("spring.redis.host")).append(":").append(environment.getProperty("spring.redis.port"));
        singleServerConfig.setAddress(address.toString());
        if (StringUtils.isNotBlank(environment.getProperty("spring.redis.password"))) {
            singleServerConfig.setPassword(environment.getProperty("spring.redis.password"));
        }
        return Redisson.create(config);
    }


    @Bean
    @Primary
    public ValueOperations getValueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    @Bean
    @Primary
    public ListOperations getListOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    @Primary
    public HashOperations getHashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    @Primary
    public SetOperations getSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    @Primary
    public ZSetOperations getZSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

}