package com.hope.filmweb.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.zjc.utils.IpUtils;
import com.zjc.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @Author: zhangjiachen
 * @Date: 2018/12/17 20:58
 * @Description: 请求过滤器
 */
@Configuration
@Order(1)
@WebFilter(filterName = "ipFilter", urlPatterns = {"/*"})
public class IpFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(IpFilter.class);

    @Autowired
    private RedisUtils redisUtils;


    private static String img = "/img";
    private static String css = "/css";
    private static String js = "/js";

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器初始化。。。{}");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (urlCheck(request.getRequestURI())) {
            //如果是静态资源直接放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (!ipCheck(request)) {
            PrintWriter writer;
            OutputStreamWriter osw;
            try {
                response.setCharacterEncoding("UTF-8");
                osw = new OutputStreamWriter(response.getOutputStream(),
                        "UTF-8");
                writer = new PrintWriter(osw, true);
                String jsonStr = JSONUtils.toJSONString("请不要频繁刷新!");
                writer.write(jsonStr);
                writer.flush();
                writer.close();
                osw.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("过滤异常!", e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("过滤器销毁。。。");
    }

    /**
     * 校验IP
     *
     * @param:
     * @return:
     * @author:Zhang jc
     * @date: 2018/12/22 11:13
     */
    private boolean ipCheck(HttpServletRequest request) {
        String ip = IpUtils.getIpAddr(request);
        logger.info("请求进来了，请求ip为：{},名称为：{},端口为:{}", ip,
                request.getRemoteHost(), request.getRemotePort());
        Boolean isExist = redisUtils.hasKey(ip);
        logger.info("::::::::::::::::::::::::{}", ip);
        if (isExist) {
            logger.info("这逼访问的有点频繁啊，不让访问!!");
            return false;
        }
        logger.info("正常访问...");
        redisUtils.set(ip, ip, 2);
        return true;
    }

    /**
     * 校验url是否需要过滤
     *
     * @param:
     * @return:
     * @author:Zhang jc
     * @date: 2018/12/25 10:09
     */
    private boolean urlCheck(String url) {
        if (url.contains(img) || url.contains(js) || url.contains(css)) {
            return true;
        }
        return false;
    }
}
