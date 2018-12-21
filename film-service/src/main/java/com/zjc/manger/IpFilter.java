package com.zjc.manger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/17 20:58
 * @Description: 请求过滤器
 */
@Component
@WebFilter(filterName = "ipFilter", urlPatterns = {"/*"})
@Slf4j
public class IpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        test(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁。。。");
    }

    private void test(ServletRequest request) {
        log.info("请求进来了，请求ip为：{},名称为：{},端口为:{}", request.getRemoteAddr(),
                request.getRemoteHost(), request.getRemotePort());
    }
}
