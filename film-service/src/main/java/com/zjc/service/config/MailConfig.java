package com.zjc.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 邮件配置
 *
 * @Author: zhangjiachen
 * @Date: 2018/12/28 10:44
 * @Description:
 */
@Data
@Configuration
@PropertySource("classpath:/mail.properties")
public class MailConfig {
    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    @Value("${mail.transport.protocol}")
    private String transportProtocol;

    @Value("${mail.send.charset}")
    private String sendCharset;

    @Value("${mail.smtp.port}")
    private int smtpPort;

    @Value("${mail.ssl}")
    private boolean ssl;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.auth.name}")
    private String authName;

    @Value("${mail.auth.password}")
    private String authPassword;

    @Value("${mail.smtp.timeout}")
    private String smtpTimeout;
}
