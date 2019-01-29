package com.zjc.service.feign;

import com.zjc.service.serviceimpl.feign.FeignTestImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试feign
 *
 * @Author: zhangjiachen
 * @Date: 2019/1/28 15:19
 * @Description:
 */
@FeignClient(value = "film-web", fallback = FeignTestImpl.class)
public interface FeignTest {

    /**
     * 调用远程eureka服务发送邮件
     *
     * @param user 发件人
     * @return 发送结果
     */
    @RequestMapping(value = "/testSendMail", method = RequestMethod.GET)
    String testSendMail(@RequestParam(value = "user") String user);
}
