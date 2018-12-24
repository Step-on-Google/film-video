//package com.hope.filmweb.interceptor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * 注册拦截器
// *
// * @Author: zhangjiachen
// * @Date: 2018/12/24 19:18
// * @Description:
// */
//@Configuration
//@EnableWebMvc
//public class RegisterInterceptor extends WebMvcConfigurerAdapter {
//    @Bean
//    IpIntercepte localInterceptor() {
//        return new IpIntercepte();
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localInterceptor());
//    }
//}
