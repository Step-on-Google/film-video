package com.zjc.utils;/* com.zjc.utils.RandomUtils.java created at 2016年3月29日下午2:11:37 by xingjie.zhou
 * 
 * 汇付天下有限公司
 * Copyright (c) 2006-2016 ChinaPnR, Inc. All rights reserved.
 */


import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

/**
 * @author:Zhang jc
 * @date: 2018/10/11 18:25
 * @description:
 */
public class RandomUtils {

    /**
     * 不允许实例化
     * 说明
     */
    private RandomUtils() {

    }

    /**
     * 随机生成指定长度的字符和数字组合的字符串
     *
     * @param length
     * @return
     */
    public static String randomStr(int length) {
        return RandomStringUtils.random(length, true, true).toLowerCase();
    }

    /**
     * 生成20位流水
     *
     * @return
     */
    public static String randomSeq() {
        return StringUtil.concatStrs(DateUtils.getCurrentDateTime(),
                RandomStringUtils.random(6, false, true));
    }

    /**
     * 生成n位随机数
     *
     * @param n
     * @return
     */
    public static int getRandomNumber(int n) {
        int temp = 0;
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n);
        Random rand = new Random();
        while (true) {
            temp = rand.nextInt(max);
            if (temp >= min) {
                break;
            }
        }
        return temp;
    }
}
