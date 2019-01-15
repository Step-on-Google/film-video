//package com.hope.filmweb.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.ByteArrayDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.AbstractMessageListenerContainer;
//
//import java.util.HashMap;
//
///**
// * kafka配置
// *
// * @Author: zhangjiachen
// * @Date: 2019/1/11 15:33
// * @Description: 配置kafka bean config
// */
//@Configuration
//@Slf4j
//public class KafkaConfig {
//
//    @Bean
//    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory();
//        factory.setConcurrency(1);
//        factory.setConsumerFactory(consumerFactory());
//        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
//        factory.setBatchListener(true);
//        //设置提交偏移量的方式
//        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
//        return factory;
//    }
//
//    public ConsumerFactory<String, byte[]> consumerFactory() {
//        return new DefaultKafkaConsumerFactory(new HashMap(16), new StringDeserializer(), new ByteArrayDeserializer());
//    }
//
////    public Map<String, Object> consumerConfigs() {
////        Map<String, Object> propsMap = new HashMap(16);
////        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
////        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
////        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
////        propsMap.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatInterval);
////        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
////        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
////        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
////        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
////
////        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);//每个批次获取数
////        return propsMap;
////    }
//
//}
