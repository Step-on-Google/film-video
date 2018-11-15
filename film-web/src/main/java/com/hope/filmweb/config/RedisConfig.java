package com.hope.filmweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/14 16:35
 * @Description: redis配置
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Autowired
    private JedisConnectionFactory connectionFactory;

    @Bean(name = "redisTemplate")
    public RedisTemplate<Serializable, Object> getTemplate() {
        RedisTemplate<Serializable, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
