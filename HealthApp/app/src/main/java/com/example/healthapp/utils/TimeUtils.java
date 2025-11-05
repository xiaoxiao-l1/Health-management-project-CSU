package com.example.healthapp.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {


    // 是星期几
    public static String dateToWeek(String datetime) throws ParseException {
        String rq = datetime;
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rq);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1 < 0 ? 0 : cal.get(Calendar.DAY_OF_WEEK) - 1;
        String week = weekDays[w];
        return week;
    }
    public static String secondsToHMS(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public static String getYYYMMDD(String time) {
        if (TextUtils.isEmpty(time)){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return simpleDateFormat.format(parse);
    }

    //当前日期和 截至日期相差多少天
    public static int differentDaysByMillisecond(String endTime) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return differentDaysByMillisecond(new Date(), date1);
    }

    public static int differentDaysByMillisecond(String startTime, String endTime) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        return differentDaysByMillisecond(date, date1);
    }

    public static boolean differentDaysByMillisecond1(String startTime, String endTime) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (date.getTime()<date1.getTime()) {
            return true;
        }
        return false;

    }

    //判断两个日期相隔多少天
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static long getYYYYmmddMillisecond(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = dateFormat.parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());

    }
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());

    }
}
