package com.hope.filmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author:Zhang jc
 * @date: 2018/11/16 10:42
 * @description:
 */
@SpringBootApplication(scanBasePackages = {"com.*"})
@ServletComponentScan
public class FilmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmWebApplication.class, args);
    }
}
