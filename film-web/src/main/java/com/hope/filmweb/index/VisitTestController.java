package com.hope.filmweb.index;

import com.hope.filmweb.config.MailService;
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
            String content = "身体是宝贵的。但是在生命历程中我们往往会过分的追求或是不加控制沉迷于身体的享受，以至于被这些“路边的精彩(外物)”所羁绊，劳累了身体，使它受到伤害，大多人直至晚年醒悟，但为时已晚。\n" +
                    "\n" +
                    "思想则是我们的天赋，它诞生了我们的灵魂，它是我们进化的珍贵礼物，它接收身体所带来的一切感受，它帮助我们分析、总结、感悟这个世界，它使我们不断完善、进步并了解事物的真谛，它能够帮助我们破除迷茫、困惑、悔恨，使我们念头通达、自由。同时，思想也是我们一切烦恼、罪恶的根源。\n" +
                    "\n" +
                    "想要达到思想境界的升华，只有明析本质，做到坚持学习、反思、悔过，并学会理解和克制思想。\n" +
                    "\n" +
                    "试想，当无思想负担之忧、无身体迫害忧虑之患。那么，我们的人生一定是美丽的、宝贵的。\n" +
                    "\n" +
                    "外在的身体及内在的思想，就像阴阳两极一般，相互对立，却又相互影响，不断的运作，演绎着我们生命。想要两者的平衡循环，那么不管是身体或思想都不可以不加控制的一味输出，而忽略了保养、蕴藏。只有进行恰当的身体和思想上的保养、蕴藏，我们人类的潜力才可以更大化的挖掘。";
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
