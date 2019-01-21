package com.zjc.service.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import com.zjc.service.config.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/28 10:39
 * @Description: 发送邮件
 */
@Service("mailService")
@Slf4j
public class MailService {

    @Autowired
    private MailConfig mailConfig;

    private static String auth;
    private static String host;
    private static String protocol;
    private static int port;
    private static String authName;
    private static String password;
    private static boolean isSSL;
    private static String charset;
    private static String timeout;

    @PostConstruct
    public void initParam() {
        auth = mailConfig.getSmtpAuth();
        host = mailConfig.getHost();
        protocol = mailConfig.getTransportProtocol();
        port = mailConfig.getSmtpPort();
        authName = mailConfig.getAuthName();
        password = mailConfig.getAuthPassword();
        charset = mailConfig.getSendCharset();
        isSSL = mailConfig.isSsl();
        timeout = mailConfig.getSmtpTimeout();
    }

    /**
     * 发送邮件
     *
     * @param subject     主题
     * @param toUsers     收件人
     * @param ccUsers     抄送
     * @param content     邮件内容
     * @param attachfiles 附件列表  List<Map<String, String>> key: name && file
     */
    public boolean sendEmail(String subject, String[] toUsers, String[] ccUsers, String content, List<Map<String, String>> attachfiles) {
        boolean flag = true;
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(host);
            javaMailSender.setUsername(authName);
            javaMailSender.setPassword(password);
            javaMailSender.setDefaultEncoding(charset);
            javaMailSender.setProtocol(protocol);
            javaMailSender.setPort(port);

            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", auth);
            properties.setProperty("mail.smtp.timeout", timeout);
            if (isSSL) {
                MailSSLSocketFactory sf = null;
                try {
                    sf = new MailSSLSocketFactory();
                    sf.setTrustAllHosts(true);
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.ssl.socketFactory", sf);
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            javaMailSender.setJavaMailProperties(properties);

            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setTo(toUsers);
            if (ccUsers != null && ccUsers.length > 0) {
                messageHelper.setCc(ccUsers);
            }
            messageHelper.setFrom(authName);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            if (attachfiles != null && attachfiles.size() > 0) {
                for (Map<String, String> attachFile : attachfiles) {
                    String attachFileName = attachFile.get("name");
                    File file = new File(attachFile.get("file"));
                    messageHelper.addAttachment(attachFileName, file);
                }
            }
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("发送邮件失败!", e);
            flag = false;
        }
        return flag;
    }
}
