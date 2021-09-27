package com.graduation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间工具类
 */
public class DateUtils {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("M月d日");
    public static final DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("Y年M月d日");
    public static final DateTimeFormatter FORMATTER4 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER5 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


    /**
     * 获取当前时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentLocalDateTime(){
        return LocalDateTime.now().format(FORMATTER);
    }

    /**
     * 获取需要还书的时间
     * @param borrowTime 借书时间
     * @param borrowTime 借书的月份
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getReturnTime(String borrowTime,Long month){
        String format = LocalDateTime.parse(borrowTime, FORMATTER).format(FORMATTER4);
        return LocalDate.parse(format,FORMATTER4).plusMonths(month).format(FORMATTER4) + " 23:59:59";
    }
}
