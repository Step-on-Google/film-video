package com.zjc.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @Auther: zhangjiachen
 * @Date: 2018/10/11 18:27
 * @Description:
 */
public class StringUtil {
    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String concatStrs(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (StringUtils.isNotBlank(str)) {
                sb.append(str.trim());
            }
        }
        return sb.toString();
    }
}
