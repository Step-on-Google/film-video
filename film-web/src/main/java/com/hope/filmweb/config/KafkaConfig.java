package com.hope.filmweb.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka配置
 *
 * @Author: zhangjiachen
 * @Date: 2019/1/11 15:33
 * @Description: 配置kafka bean config
 */
@Configuration
@Slf4j
@EnableKafka
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private int autoCommitInterval;

    @Value("${spring.kafka.consumer.heartbeat-interval}")
    private int heartbeatInterval;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private int maxPollRecords;

    @Value("${spring.kafka.listener.concurrency}")
    private int concurrency;

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<String, byte[]>();
        factory.setConcurrency(concurrency);
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);//设置提交偏移量的方式
        return factory;
    }

    public ConsumerFactory<String, byte[]> consumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfigs(), new StringDeserializer(), new ByteArrayDeserializer());
    }

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<String, Object>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);//每个批次获取数
        return propsMap;
    }

}
