package com.hope.filmweb.controller.index;

import com.alibaba.fastjson.JSON;
import com.zjc.dao.entity.TestTable;
import com.zjc.service.index.IndexService;
import com.zjc.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            ListenableFuture zjcTopic = kafkaTemplate.send("zjcTopic1", "1", "张嘉琛!".getBytes());
            zjcTopic.addCallback(new ListenableFutureCallback() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.warn("发送失败！");
                }

                @Override
                public void onSuccess(@Nullable Object o) {
                    log.warn("发送成功！");
                }
            });
        } catch (Exception e) {
            log.error("kafka异常!", e);
            return "kafkaTest error";
        }
        return "kafkaTest success";
    }

    @KafkaListener(id = "zjc", topics = "zjcTopic1")
    public void kafkaListener(List<ConsumerRecord> records, Acknowledgment ack) {
        try {
            for (ConsumerRecord record : records) {
                Date date = new Date();
                date.setTime(record.timestamp());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info("value:{},time:{}", new String((byte[]) record.value()), sdf.format(date));
            }
        } catch (Exception e) {
            log.error("kafka 消费者异常!", e);
        } finally {
            //手动提交偏移量
            ack.acknowledge();
        }
    }

}
