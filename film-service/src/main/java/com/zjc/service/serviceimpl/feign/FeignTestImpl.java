package com.zjc.service.serviceimpl.feign;

import com.zjc.service.feign.FeignTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangjiachen
 * @Date: 2019/1/29 11:10
 * @Description:
 */
@Slf4j
@Component
public class FeignTestImpl implements FeignTest {
    @Override
    public String testSendMail(String user) {
        log.warn("调用GG，什么都不处理!");
        return null;
    }
}
