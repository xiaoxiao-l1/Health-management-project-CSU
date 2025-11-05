package com.example.healthapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库版本号
    private static Integer Version = 2;
    public final static String tableName = "storage";

    public DatabaseHelper(@Nullable Context context) {
        super(context, tableName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
        String sql = "create table if not exists user(id integer primary key autoincrement,name varchar(64),psw varchar(64),sex varchar(64),des varchar(64),nickname varchar(64))";
        db.execSQL(sql);

        String sql_Post = "create table if not exists Post(id integer primary key autoincrement,authorid integer,authorname varchar(64),authorimg text,title text,content text,type text,imgsjson text,files text,createtime text)";
        db.execSQL(sql_Post);

        String sql_PostComments = "create table if not exists PostComments(id integer primary key autoincrement,postid integer,userid integer,content text,createtime text,userName varchar(64),avatar text,usertype integer,state integer)";
        db.execSQL(sql_PostComments);

        String sql_healthinfo = "create table if not exists healthinfo(id integer primary key autoincrement,userid integer,username varchar(64),bloodpressure varchar(64),bloodsugar varchar(64),heartrate varchar(64),des text,time text)";
        db.execSQL(sql_healthinfo);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
