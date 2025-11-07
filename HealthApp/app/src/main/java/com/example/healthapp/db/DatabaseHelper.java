package com.example.healthapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库版本号
    private static Integer Version = 2;

    // 表名常量
    private static final String TABLE_USER = "user";
    private static final String TABLE_POST = "Post";
    private static final String TABLE_POST_COMMENTS = "PostComments";
    private static final String TABLE_HEALTH_INFO = "healthinfo";

    // 创建表SQL语句
    private static final String CREATE_TABLE_USER =
            "create table if not exists " + TABLE_USER + "(id integer primary key autoincrement,name varchar(64),psw varchar(64),sex varchar(64),des varchar(64),nickname varchar(64))";

    private static final String CREATE_TABLE_POST =
            "create table if not exists " + TABLE_POST + "(id integer primary key autoincrement,authorid integer,authorname varchar(64),authorimg text,title text,content text,type text,imgsjson text,files text,createtime text)";

    private static final String CREATE_TABLE_POST_COMMENTS =
            "create table if not exists " + TABLE_POST_COMMENTS + "(id integer primary key autoincrement,postid integer,userid integer,content text,createtime text,userName varchar(64),avatar text,usertype integer,state integer)";

    private static final String CREATE_TABLE_HEALTH_INFO =
            "create table if not exists " + TABLE_HEALTH_INFO + "(id integer primary key autoincrement,userid integer,username varchar(64),bloodpressure varchar(64),bloodsugar varchar(64),heartrate varchar(64),des text,time text)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "health_app.db", null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_POST);
        db.execSQL(CREATE_TABLE_POST_COMMENTS);
        db.execSQL(CREATE_TABLE_HEALTH_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 可以根据版本号进行数据库升级
        if (oldVersion < 2) {
            // 版本1到版本2的升级逻辑
            // 例如：添加新表或修改表结构
        }
    }
}