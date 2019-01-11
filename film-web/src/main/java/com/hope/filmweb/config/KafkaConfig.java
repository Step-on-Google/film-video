//package com.hope.filmweb.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
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
//    /**
//     * 获取kafkaTemplate
//     *
//     * @param:
//     * @return:
//     * @author:Zhang jc
//     * @date: 2019/1/11 15:34
//     */
//    @Bean(name = "kafkaTemplate")
//    public KafkaTemplate getKafka() {
//        Map<String, Object> configs = new HashMap(16);
//        configs.put("bootstrap.servers", "47.100.237.222");
//        DefaultKafkaProducerFactory kafkaProducerFactory = new DefaultKafkaProducerFactory(configs);
//        kafkaProducerFactory.setKeySerializer(new StringSerializer());
//        kafkaProducerFactory.setProducerPerConsumerPartition(true);
//        kafkaProducerFactory.setValueSerializer(new StringSerializer());
//        KafkaTemplate kafkaTemplate = new KafkaTemplate(kafkaProducerFactory);
//        log.info("初始化kafka成功!{}", kafkaTemplate.getMessageConverter());
//
//
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "47.100.237.222:9092");
//        props.put("acks", "all");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("linger.ms", 1);
//        props.put("buffer.memory", 33554432);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        Producer<String, String> producer = new KafkaProducer<>(props);
//        for(int i = 0; i < 100; i++)
//            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
//
//        producer.close();
//        return kafkaTemplate;
//    }
//
//}
