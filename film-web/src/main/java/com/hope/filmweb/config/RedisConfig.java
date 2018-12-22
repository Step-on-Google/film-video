package com.hope.filmweb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: zhangjiachen
 * @Date: 2018/11/14 16:35
 * @Description: redis配置
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {
//    @Autowired
//    private JedisConnectionFactory connectionFactory;

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory getRedisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName("");
        jedisConnectionFactory.setDatabase(0);
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }
//
//    @Bean(name = "redisTemplate")
//    public RedisTemplate<String, Object> getTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
//        RedisConnectionFactory connectionFactory = getRedisConnectionFactory();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.opsForValue().set("zjc", "张嘉琛");
//        Object zjc = redisTemplate.opsForValue().get("zjc");
//        log.info("templete:{}", String.valueOf(zjc));
//        return redisTemplate;
//    }

    @Bean(name = "jedis")
    public Jedis getJedis() {
        Jedis jedis = new Jedis("47.100.237.222", 6379);
        jedis.set("zjc", "张嘉琛");
        String zjc = jedis.get("zjc");
        log.info("zjc::::::::::::::::{}", zjc);
        return jedis;
    }

    @Bean(name = "jedisPool")
    public JedisPool getJedisPool() {
        JedisPool jedisPool = new JedisPool("47.100.237.222", 6379);
        Jedis jedis = jedisPool.getResource();
        log.info("打印redis连接池{}", jedis.get("zjc"));
        return jedisPool;
    }


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


}
