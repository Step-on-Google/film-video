package com.hope.filmweb.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean类型过滤器配置
 *
 * @Author: zhangjiachen
 * @Date: 2018/12/25 9:52
 * @Description:
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<IpFilter> loggingFilter() {
        FilterRegistrationBean<IpFilter> registrationBean
                = new FilterRegistrationBean();
        registrationBean.setFilter(new IpFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
