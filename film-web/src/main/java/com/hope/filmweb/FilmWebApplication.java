package com.hope.filmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class FilmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmWebApplication.class, args);
    }
}
