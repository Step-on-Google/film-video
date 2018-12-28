package com.zjc.service.dao.dao.utils; /**
 * 汇付天下有限公司
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:Zhang jc
 * @date: 2018/10/11 18:29
 * @description:
 */
public class DateUtils {


    //============================0.获取当前时间====================================

    /**
     * 获取当前日期类型时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取当前时间戳
     */
    public static long getNowTimestamp() {
        return getNow().getTime();
    }

    /**
     * 获取当前日期 yyyyMMdd
     */
    public static String getCurrentDate() {
        return toMailDateDtPartString(getNow());
    }

    /**
     * 获取当期时间HHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        return toMailTimeTmPartString(getNow());
    }

    /**
     * 获取当前日期和时间yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return toMailDateString(getNow());
    }

    /**
     * 将一个日期型转换为指定格式字串
     *
     * @param aDate
     * @param formatStr
     * @return
     */
    public static final String toFormatDateString(Date aDate, String formatStr) {
        if (aDate == null) {
            return StringUtils.EMPTY;
        }
        return new SimpleDateFormat(formatStr).format(aDate);

    }

    /**
     * 将一个日期型转换为'yyyyMMddHHmmss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailDateString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_FORMAT);
    }

    /**
     * 将一个日期型转换为'yyyyMMdd'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailDateDtPartString(Date aDate) {
        return toFormatDateString(aDate, MAIL_DATE_DT_PART_FORMAT);
    }

    /**
     * 将一个日期型转换为'HHmmss'格式字串
     *
     * @param aDate
     * @return
     */
    public static final String toMailTimeTmPartString(Date aDate) {
        return toFormatDateString(aDate, MAIL_TIME_TM_PART_FORMAT);
    }

    /**
     * 验证日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != MAIL_DATE_DT_PART_FORMAT.length()) {
            return false;
        }
        try {
            Integer.parseInt(strDate);
        } catch (Exception numberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat("yyyyMMdd");
        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidShortTimeFormat(String strTime) {
        if (strTime.length() != MAIL_TIME_TM_PART_FORMAT.length()) {
            return false;
        }
        try {
            Integer.parseInt(strTime);
        } catch (Exception numberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(MAIL_TIME_TM_PART_FORMAT);
        try {
            df.parse(strTime);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    public static final String MAIL_DATE_DT_PART_FORMAT = "yyyyMMdd";
    public static final String MAIL_TIME_TM_PART_FORMAT = "HHmmss";
    public static final String MAIL_DATE_FORMAT = "yyyyMMddHHmmss";

    private DateUtils() {
    }

    /**
     * 计算两个日期之间相差的月数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final int getDifferenceMonths(Date startDate, Date endDate) {
        if (null == startDate || null == endDate) {
            throw new IllegalArgumentException("比较日期不能为空!");
        }
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        return Math.abs((startCal.get(Calendar.YEAR) - endCal.get(Calendar.YEAR)) * 12
                + (startCal.get(Calendar.MONTH) - endCal.get(Calendar.MONTH)));
    }

    private static final Date addTime(Date aDate, int timeType, int amount) {
        if (aDate == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(aDate);
        cal.add(timeType, amount);
        return cal.getTime();
    }

    /**
     * 为一个日期加上指定小时数
     *
     * @param aDate
     * @param amount 小时数
     * @return
     */
    public static final Date addHours(Date aDate, int amount) {
        return addTime(aDate, Calendar.HOUR, amount);
    }
}
