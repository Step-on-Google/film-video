package com.hope.filmweb.filter;

import com.alibaba.druid.support.json.JSONUtils;
import com.zjc.utils.IpUtils;
import com.zjc.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
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
public class IpFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(IpFilter.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("过滤器初始化。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
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
}
