package com.hope.filmweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/14 16:35
 * @Description: redis配置
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {
    @Autowired
    private JedisConnectionFactory connectionFactory;

    @Bean(name = "redisTemplate")
    public RedisTemplate<Serializable, Object> getTemplate() {
        RedisTemplate<Serializable, Object> redisTemplate = new RedisTemplate();
        log.info("开始打印连接{},port{}", connectionFactory.getClientName(), connectionFactory.getPort());
        log.info("host{}", connectionFactory.getClientName());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean(name = "jedis")
    public Jedis getJedis() {
        Jedis jedis = new Jedis("47.100.237.222", 6379);
        jedis.set("zjc", "张嘉琛");
        String zjc = jedis.get("zjc");
        log.info("zjc::::::::::::::::{}", zjc);
        return jedis;
    }


}
