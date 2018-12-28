//package com.hope.filmweb.interceptor;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import IpUtils;
//import RedisUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
///**
// * IP拦截器
// *
// * @Author: zhangjiachen
// * @Date: 2018/12/24 19:15
// * @Description:
// */
//@Slf4j
//public class IpIntercepte implements HandlerInterceptor {
//
//    private static Logger logger = LoggerFactory.getLogger(IpIntercepte.class);
//
//    @Autowired
//    private RedisUtils redisUtils;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        log.info("拦截器····················");
//        if (!ipCheck(request)) {
//            PrintWriter writer;
//            OutputStreamWriter osw;
//            try {
//                response.setCharacterEncoding("UTF-8");
//                osw = new OutputStreamWriter(response.getOutputStream(),
//                        "UTF-8");
//                writer = new PrintWriter(osw, true);
//                String jsonStr = JSONUtils.toJSONString("请不要频繁刷新!");
//                writer.write(jsonStr);
//                writer.flush();
//                writer.close();
//                osw.close();
//                return false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                logger.error("过滤异常!", e);
//            }
//        }
//        return true;
//    }
//
//
//    /**
//     * 校验IP
//     *
//     * @param:
//     * @return:
//     * @author:Zhang jc
//     * @date: 2018/12/22 11:13
//     */
//    private boolean ipCheck(HttpServletRequest request) {
//        String ip = IpUtils.getIpAddr(request);
//        logger.info("请求进来了，请求ip为：{},名称为：{},端口为:{}", ip,
//                request.getRemoteHost(), request.getRemotePort());
//        Boolean isExist = redisUtils.hasKey(ip);
//        logger.info("::::::::::::::::::::::::{}", ip);
//        if (isExist) {
//            logger.info("这逼访问的有点频繁啊，不让访问!!");
//            return false;
//        }
//        logger.info("正常访问...");
//        redisUtils.set(ip, ip, 2);
//        return true;
//    }
//}
