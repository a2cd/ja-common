package org.caseor.common.core.util;


import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {
    public static final String CH_M = "yyyy-MM";
    public static final String CH_D = "yyyy-MM-dd";
    public static final String CH_H = "yyyy-MM-dd HH";
    public static final String CH_MM = "yyyy-MM-dd HH:mm";
    public static final String CH_S = "yyyy-MM-dd HH:mm:ss";
    public static final String CH_SS = "HH:mm:ss";
    public static final String CP_M = "yyyyMM";
    public static final String CP_D = "yyyyMMdd";
    public static final String CP_H = "yyyyMMddHH";
    public static final String CP_MM = "yyyyMMddHHmm";
    public static final String CP_S = "yyyyMMddHHmmss";
    public static final String CP_SS = "HHmmss";
    public static final String CN_H = "yyyyMMdd'T'HH";
    public static final String CN_MM = "yyyyMMdd'T'HHmm";
    public static final String CN_S = "yyyyMMdd'T'HHmmss";
    public static final String CM_TS = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String CM_B = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String CM_T = "yyyy-MM-dd'T'HH:mm:ss:SSS";
    public static final String CM_S = "yyyyMMddHHmmssSSS";
    public static final String CI_S = "yyyy/MM/dd HH:mm:ss";
    public static final String CM_TB = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String CM_BS = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 获取当前Date型日期
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(CH_D);
    }

    public static String getTime() {
        return dateTimeNow(CH_S);
    }

    public static String dateTimeNow() {
        return dateTimeNow(CP_S);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(CH_D, date);
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }
}
