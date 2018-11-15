package com.hope.filmweb.index;

import com.zjc.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Zhang jc
 * @date: 2018/10/9 16:52
 * @description:
 */
@RestController
public class VisitTestController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "helloWrod~!";
    }

    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public String testRedis() {
        indexService.showIndexData();
        return "redis success";
    }

}
