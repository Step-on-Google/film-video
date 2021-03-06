package com.hope.filmweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/14 16:35
 * @Description: redis配置
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

//    @Bean(name = "jedisPool")
//    public JedisPool getJedisPool() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(8);
//        poolConfig.setMinIdle(0);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        poolConfig.setNumTestsPerEvictionRun(10);
//        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
//        poolConfig.setTestOnReturn(true);
//        JedisPool jedisPool = new JedisPool(poolConfig, "47.100.237.222", 6379);
//        log.info("redis连接池注入成功！");
//        return jedisPool;
//    }


//    @Bean
//    public JedisCluster getJedisCluster() {
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        jedisClusterNodes.add(new HostAndPort("47.100.237.222", 6379));
//        JedisCluster jc = new JedisCluster(jedisClusterNodes);
//        jc.set("foo", "bar");
//        String value = jc.get("foo");
//        log.info("foo::::::::::::::::{}", value);
//        return jc;
//    }


//    @Bean(name = "jedisPoolConfig")
//    public JedisPoolConfig getJedisPoolConfig() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxIdle(8);
//        poolConfig.setMinIdle(0);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        poolConfig.setNumTestsPerEvictionRun(10);
//        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
//        poolConfig.setTestOnReturn(true);
//        return poolConfig;
//    }

//    @Bean(name = "jedisConnectionFactory")
//    public JedisConnectionFactory getJedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setPoolConfig(getJedisPoolConfig());
//        jedisConnectionFactory.setHostName("47.100.237.222");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setDatabase(0);
//        jedisConnectionFactory.setTimeout(2000);
//        return jedisConnectionFactory;
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);
        log.info("RedisTemplate 注入成功!");
        return redisTemplate;
    }


}
