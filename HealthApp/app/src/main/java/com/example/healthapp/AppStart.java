package com.example.healthapp;

import android.app.Application;

import com.example.healthapp.utils.SpUtil;


public class AppStart extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sp
        SpUtil.init(getApplicationContext(),"cache");
    }

}
