package com.zjc.manger;

import com.zjc.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/17 20:58
 * @Description: 请求过滤器
 */
@Component
@WebFilter(filterName = "ipFilter", urlPatterns = {"/*"})
@Slf4j
public class IpFilter implements Filter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!ipCheck(servletRequest)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("https://www.baidu.com/");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁。。。");
    }

    /**
     * 校验IP
     *
     * @param:
     * @return:
     * @author:Zhang jc
     * @date: 2018/12/22 11:13
     */
    private boolean ipCheck(ServletRequest request) {
        log.info("请求进来了，请求ip为：{},名称为：{},端口为:{}", request.getRemoteAddr(),
                request.getRemoteHost(), request.getRemotePort());
        String ip = redisUtils.getKey(request.getRemoteAddr());
        log.info("::::::::::::::::::::::::{}", ip);
        if (StringUtils.isNotBlank(ip)) {
            log.info("这逼访问的有点频繁啊，不让访问!!");
            return false;
        }
        log.info("正常访问...");
        redisUtils.setKeySecond(request.getRemoteAddr(), request.getRemoteAddr(), 2);
        return true;
    }
}
