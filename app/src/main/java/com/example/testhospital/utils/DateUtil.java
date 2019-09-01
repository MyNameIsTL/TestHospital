package com.example.testhospital.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * Created by TL on 2017-04-18.
 */

public class DateUtil {
    public static final String FULL_DATE_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FULL_DATE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_NO_SYMBOL = "yyyyMMddHHmmss";
    public static final String SHORT_DATE = "yyyyMMdd";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String FULL_DATE_SSS_NO_SYMBOL = "yyyyMMddHHmmssSSS";
    public static final String FULL_DATE_SS_NO_SYMBOL = "yyyyMMddHHmmssSS";
    public static final String MONTH_YEAR_NO_SYMBOL = "MMyy";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String DATE = "dd";
    public static final String MONTH = "MM";
    public static final String YEAR = "yyyy";
    public static final String HOUR = "HH";
    public static final String HOUR_MINUTE = "HH:mm";
    public static final String MONTH_DAY = "MM月dd日";
    public static final String YEAR_MONTH_DATE = "yyyyMMdd";
    public static final String YEAR_MONTH_ = "yyyy年MM月";
    public static final String YEAR_MONTH_DAY_NO_SYMBOL = "yyyyMMdd";
    public static final String YEAR_MONTH_NO_SYMBOL = "yyyyMM";
    public static final String YEAR_MONTH_DAY = "yyyy年MM月dd日";
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String YEAR_MONTH_DAY_SLASH = "yyyy/MM/dd";

    public static long mTimeOffset = 0;

    public static void setTimeOffset(String serTime) {
        Date serDate = parseDate(serTime, FULL_DATE);
        mTimeOffset = serDate.getTime() - System.currentTimeMillis();
    }

    /**
     * 格式化一个日期
     *
     * @param date 日期
     * @return 结果
     */
    public static String formatDate(Date date) {
        return formatDate(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 以指定的格式来格式化一个日期
     *
     * @param date    日期
     * @param pattern 格式
     * @return 结果
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static Date parseDate(String date) {
        return parseDate(date, DEFAULT_DATE_PATTERN);
    }


    public static Date parseDate(String date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化一个long类型的日期
     *
     * @param time 日期时间
     * @return 结果
     */
    public static String formatLong(long time) {
        return formatLong(time, DEFAULT_DATE_PATTERN);
    }

    /**
     * 以指定的格式来格式化一个long类型的日期
     *
     * @param time    时间
     * @param pattern 格式
     * @return 结果
     */
    public static String formatLong(long time, String pattern) {
        Date date = new Date(time);
        return formatDate(date, pattern);
    }

    /**
     * 格式化当前时间
     *
     * @return 结果
     */
    public static String formatCurrent() {
        return formatCurrent(DEFAULT_DATE_PATTERN);
    }

    /**
     * 以指定的格式来格式化当前时间
     *
     * @param pattern 指定的格式
     * @return 结果
     */
    public static String formatCurrent(String pattern) {
        Date date = new Date(System.currentTimeMillis() + mTimeOffset);
        return formatDate(date, pattern);
    }

    /**
     * 将时间“2015-5-2”格式转换为“2015-05-02”
     *
     * @param someday
     * @return
     */
    public static String formatSomeday(String someday) {
        Date date;
        String time = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(someday);
            time = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 日期加减。
     *
     * @param base 基础日期
     * @param days 增加天数(减天数则用负数)
     * @return 计算结果
     */
    public static Date datePlus(Date base, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(base);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * @param time
     * @return
     */
    public static String addLineTime(String time) {
        if (time.length() == 8) {
            time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-" + time.substring(6, 8);
        }
        return time;
    }

    public static String formatDateUpload(String DateStr) {
        if (DateStr == null) return "";
        try {
            return DateStr
                    .replace("-", "")
                    .replace(":", "")
                    .replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        //获得当前时间的月份，月份从0开始所以结果要加1
        return calendar.get(Calendar.MONTH) + 1;
    }
}
