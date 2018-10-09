package com.hope.filmweb.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class visitTest {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "helloWrod~!";
    }

}
