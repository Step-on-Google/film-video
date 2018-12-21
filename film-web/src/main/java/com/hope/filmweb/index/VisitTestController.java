package com.hope.filmweb.index;

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

}
