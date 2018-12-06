package com.hope.filmweb.index;

import com.zjc.index.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
        log.info("请求进来了，请求ip为：{},名称为：{},端口为:{}", request.getRemoteAddr(),
                request.getRemoteHost(), request.getRemotePort());
        return "html/index";
    }

    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    @ResponseBody
    public String testRedis() {
        indexService.showIndexData();
        return "redis success";
    }

}
