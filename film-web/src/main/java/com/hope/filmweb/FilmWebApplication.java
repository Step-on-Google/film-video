package com.hope.filmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author:Zhang jc
 * @date:  2018/11/16 10:42
 * @description:
 */
@SpringBootApplication(scanBasePackages = {"com.*"})
public class FilmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmWebApplication.class, args);
    }
}
