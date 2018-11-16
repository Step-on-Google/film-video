package com.hope.filmweb.index;

import com.zjc.index.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:Zhang jc
 * @date: 2018/10/9 16:52
 * @description:
 */
@Controller
public class VisitTestController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "/html/index";
    }

    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    @ResponseBody
    public String testRedis() {
        indexService.showIndexData();
        return "redis success";
    }

}
