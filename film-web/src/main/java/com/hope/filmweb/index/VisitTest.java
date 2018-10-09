package com.hope.filmweb.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Zhang jc
 * @date: 2018/10/9 16:52
 * @description:
 */
@RestController
public class VisitTest {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "helloWrod~!";
    }

}
