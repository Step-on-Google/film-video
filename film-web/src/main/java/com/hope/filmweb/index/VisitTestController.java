package com.hope.filmweb.index;

import com.zjc.index.MailService;
import com.zjc.index.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) {
        return "html/index";
    }

    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    @ResponseBody
    public String testRedis() {
        indexService.showIndexData();
        return "redis success";
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

}
