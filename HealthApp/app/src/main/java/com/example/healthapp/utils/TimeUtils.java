package com.example.healthapp.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    // 是星期几
    public static String dateToWeek(String datetime) throws ParseException {
        if (TextUtils.isEmpty(datetime)) {
            return "";
        }
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(datetime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    public static String secondsToHMS(int seconds) {
        if (seconds < 0) seconds = 0;
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);
    }

    public static String getYYYMMDD(String time) {
        if (TextUtils.isEmpty(time)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date parse = simpleDateFormat.parse(time);
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    // 当前日期和截至日期相差多少天
    public static int differentDaysByMillisecond(String endTime) {
        if (TextUtils.isEmpty(endTime)) {
            return 0;
        }
        try {
            Date date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endTime);
            return differentDaysByMillisecond(new Date(), date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int differentDaysByMillisecond(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            return 0;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(startTime);
            Date date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(endTime);
            return differentDaysByMillisecond(date, date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean differentDaysByMillisecond1(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            return false;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(startTime);
            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(endTime);
            return date.getTime() < date1.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 判断两个日期相隔多少天
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long diff = date2.getTime() - date1.getTime();
        return (int) (diff / (1000 * 3600 * 24));
    }

    public static long getYYYYmmddMillisecond(String date) {
        if (TextUtils.isEmpty(date)) {
            return 0;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date parse = dateFormat.parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}
