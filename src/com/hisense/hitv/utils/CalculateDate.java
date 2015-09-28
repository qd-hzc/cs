package com.hisense.hitv.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author wangheping
 */
public class CalculateDate {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    }

    private static final DateFormat YYYYMMDD =
        new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat YYYY_MM_DD_HH_MM_SS =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final int NUMBER3 = 3;
    private static final int NUMBER1000 = 1000;
    @SuppressWarnings("unused")
    private static final DateFormat YYYY_MM_DD_HH_MM_SS_SSS =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private CalculateDate() {

    }

    /**
     * @return Date
     */
    public static Date today() {
        return new Date();
    }

    /**
     * @param date 日期
     * @return String
     */
    public static String formatYYYYMMDDHHMMSS(Date date) {
        return YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * @param date 日期
     * @return String
     */
    public static String formatYYYYMMDDHHMMSSSSS(Date date) {
        return YYYY_MM_DD_HH_MM_SS.format(date);
    }

    /**
     * @return String
     */
    public static String stringToday() {
        Date date = new Date();
        return YYYYMMDD.format(date);
    }

    /**
     * @param date 日期
     * @param pattern 格式
     * @return Date
     * @throws ParseException 异常
     */
    public static Date string2Date(String date, String pattern)
        throws ParseException {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.parse(date);
    }

    /**
     * @param date 日期
     * @param month 月
     * @return String
     */
    public static String addDateMonthAsString(Date date, int month) {
        return YYYYMMDD.format(addDateMonth(date, month));
    }

    /**
     * @param date 日期
     * @param second 秒
     * @return Date
     */
    public static Date addDateSecond(Date date, int second) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param hour 小时
     * @return Date
     */
    public static Date addDateHour(Date date, int hour) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param day 天
     * @return Date
     */
    public static Date addDateDay(Date date, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param week 星期
     * @return Date
     */
    public static Date addDateWeek(Date date, int week) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.WEEK_OF_MONTH, week);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param month 月
     * @return Date
     */
    public static Date addDateMonth(Date date, int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param month 月
     * @return Date
     */
    public static Date addDateQuarter(Date date, int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.MONTH, month * NUMBER3);
        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @param year 年
     * @return Date
     */
    public static Date addDateYear(Date date, int year) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * @param dateLong long型日期
     * @return Date
     */
    public static Date parseLongToDate(long dateLong) {
        return new Date(dateLong);
    }

    /**
     * @param dateLong 时间long
     * @return Date
     */
    public static Date parseSecLongToDate(long dateLong) {
        return new Date(dateLong * NUMBER1000);
    }

    /**
     * @param date 日期
     * @param hour 小时
     * @param min 分钟
     * @param sec 秒
     * @return Date
     */
    public static Date setHourMinSecond(Date date, int hour, int min, int sec) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
        calendar.set(GregorianCalendar.MINUTE, min);
        calendar.set(GregorianCalendar.SECOND, sec);

        return calendar.getTime();
    }

    /**
     * @param date 日期
     * @return int
     */
    public static int getDateMinute(Date date) {
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(date);

        return time.get(Calendar.MINUTE);
    }

    /**
     * @param date 日期
     * @return int
     */
    public static int getDateMonth(Date date) {
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(date);

        return time.get(Calendar.MONTH) + 1;
    }

    /**
     * @param date 日期
     * @return int
     */
    public static int getDateHour(Date date) {
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(date);

        return time.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @param date 日期
     * @return int
     */
    public static int getDateDay(Date date) {
        GregorianCalendar time = new GregorianCalendar();
        time.setTime(date);

        return time.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 将时间转化为秒时间戳
     * @param date
     * @return
     */
    public static long getMiliFromDate(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }
}
