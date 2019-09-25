package com.fan.wang.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author fan.wang03@hand-china.com
 * @version 1.0
 * @date 2019/9/22 : 上午10:59
 */
@Slf4j
public class DateUtils {

    //使用大写HH标识使用24小时显示格式,如果使用小写hh就表示使用12小时制格式。
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年-月-日 显示格式
     */
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    private static SimpleDateFormat simpleDateFormat;

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source  需要转换的日期
     * @param pattern 指定的格式
     * @return String   格式化后的日期
     */
    public static String DateToString(Date source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    /**
     * 时间戳转为指定格式的String类型
     *
     * @param source  通过System.currentTimeMillis()获取到的毫秒数
     * @param pattern 指定的格式
     * @return String 格式化后的结果
     */
    public static String timeStampToString(long source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * 日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date 日期
     * @return long 时间戳秒数
     */
    public static long dateToTimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    /**
     * 字符串转日期(可能会报错异常)
     *
     * @param source  字符串日期
     * @param pattern 指定的格式
     * @return String 格式化后的结果
     */
    public static Date stringToDate(String source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            log.error("字符串转换日期异常", e);
        }
        return date;
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern 指定的格式
     * @return String 格式化后的结果
     */
    public static String currentFormatDate(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }


    /**
     * 获得当前时间戳对应秒数
     *
     * @return long 秒数
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }


    /**
     * 获取当前系统时间戳
     *
     * @return Timestamp 时间戳
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }
}

