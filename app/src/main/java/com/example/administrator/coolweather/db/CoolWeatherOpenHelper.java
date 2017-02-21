package com.example.administrator.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.coolweather.model.Weatherinfo;


public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
    /**
     * Weatherinfo表建表语句
     */
    public static final String CREATE_WEATHERINFO = "create table " + Weatherinfo.TABLE_NAME + " ("
            + "id integer primary key autoincrement, "
            + Weatherinfo.INFO_COUNTYID + " integer, "
            + Weatherinfo.INFO_CITY + " text, "
            + Weatherinfo.INFO_CITYID + " text, "
            + Weatherinfo.INFO_TEMP1 + " text, "
            + Weatherinfo.INFO_TEMP2 + " text, "
            + Weatherinfo.INFO_WEATHER + " text, "
            + Weatherinfo.INFO_PTIME + " text ) ";
    /**
     * Province表建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key autoincrement, "
            + "province_name text, "
            + "province_code text)";
    /**
     * City表建表语句
     */
    public static final String CREATE_CITY = "create table City ("
            + "id integer primary key autoincrement, "
            + "city_name text, "
            + "city_code text, "
            + "province_id integer)";
    /**
     * County表建表语句
     */
    public static final String CREATE_COUNTY = "create table County ("
            + "id integer primary key autoincrement, "
            + "county_name text, "
            + "county_code text, "
            + "city_id integer)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE); // 创建Province表
        db.execSQL(CREATE_CITY); // 创建City表
        db.execSQL(CREATE_COUNTY); // 创建County表
        db.execSQL(CREATE_WEATHERINFO);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }
}
