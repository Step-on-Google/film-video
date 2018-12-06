package com.hope.filmjob.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/6 14:00
 * @Description: 测试定时任务
 */
@Component
@Slf4j
public class TestTask {

    /**
     * 每间隔2秒输出时间
     */
//    @Scheduled(cron = "0/2 * * * * ?")
//    public void logTime() {
//        log.info("定时任务，现在时间：" + System.currentTimeMillis());
//    }

}
