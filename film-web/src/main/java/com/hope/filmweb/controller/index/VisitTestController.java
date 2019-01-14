package com.hope.filmweb.controller.index;

import com.alibaba.fastjson.JSON;
import com.zjc.dao.entity.TestTable;
import com.zjc.service.index.IndexService;
import com.zjc.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author:Zhang jc
 * @date: 2018/10/9 16:52
 * @description:
 */
@Controller
@Slf4j
public class VisitTestController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private MailService mailService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "html/index";
    }

    @RequestMapping(value = "/testDataSource", method = RequestMethod.GET)
    @ResponseBody
    public String testDataSource() {
        List<TestTable> testTables = indexService.testDao();
        return JSON.toJSONString(testTables);
    }

    @RequestMapping(value = "/testSendMail", method = RequestMethod.GET)
    @ResponseBody
    public String testSendMail(HttpServletRequest request) {
        try {
            String user = request.getParameter("user");
            String subject = "紧急加班！";
            String[] toUsers = {user};
            String content = "服务器警报！！！";
            log.info("收件人:{}", user);
            if (!mailService.sendEmail(subject, toUsers, null, content, null)) {
                throw new Exception("邮件发送失败!");
            }
        } catch (Exception e) {
            log.error("测试发送邮件异常!", e);
            return "mailSend error";
        }
        return "mailSend success";
    }

    @RequestMapping(value = "/testKafka", method = RequestMethod.GET)
    @ResponseBody
    public String testKafka() {
        try {
            Map<String, String> map = new HashMap(16);
            map.put("test", "zjc");
            ListenableFuture zjcTopic = kafkaTemplate.send("zjcTopic", "1", "你好呀!");
            log.info("打印kafka返回{}", zjcTopic);
        } catch (Exception e) {
            log.error("kafka异常!", e);
            return "kafkaTest error";
        }
        return "kafkaTest success";
    }


}
