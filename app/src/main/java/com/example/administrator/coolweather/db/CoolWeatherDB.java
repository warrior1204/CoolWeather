package com.example.administrator.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.coolweather.model.City;
import com.example.administrator.coolweather.model.County;
import com.example.administrator.coolweather.model.Province;
import com.example.administrator.coolweather.model.Weatherinfo;

import java.util.ArrayList;
import java.util.List;

public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;
    public static SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
                DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    //单例模式
    private static CoolWeatherDB coolWeatherDB;
    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    //将Province实例存储到数据库。
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    // 从数据库读取全国所有的省份信息。
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor
                        .getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor
                                .getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //将City实例存储到数据库。
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    // 从数据库读取某省下所有的城市信息。
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id = ?",
                new String[] { String.valueOf(provinceId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor
                        .getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor
                        .getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        return list;
    }

    //将County实例存储到数据库。
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }

    // 从数据库读取某城市下所有的县信息。
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id = ?",
                new String[] { String.valueOf(cityId) }, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor
                        .getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor
                        .getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void saveWeather(Weatherinfo weatherinfo) {
        if (weatherinfo != null) {
            ContentValues values = new ContentValues();
            values.put(Weatherinfo.INFO_COUNTYID, weatherinfo.getCountyId());
            values.put(Weatherinfo.INFO_CITY, weatherinfo.getCity());
            values.put(Weatherinfo.INFO_CITYID, weatherinfo.getCityid());
            values.put(Weatherinfo.INFO_TEMP1, weatherinfo.getTemp1());
            values.put(Weatherinfo.INFO_TEMP2, weatherinfo.getTemp2());
            values.put(Weatherinfo.INFO_WEATHER, weatherinfo.getWeather());
            values.put(Weatherinfo.INFO_PTIME, weatherinfo.getPtime());
            db.insert(Weatherinfo.TABLE_NAME, null, values);
        }
    }

    public Weatherinfo loadWeather(int countyId) {
        Weatherinfo weatherinfo = new Weatherinfo();
        Cursor cursor = db.query(Weatherinfo.TABLE_NAME, null, Weatherinfo.INFO_COUNTYID + " = ?",
                new String[]{String.valueOf(countyId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                weatherinfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                weatherinfo.setCountyId(cursor.getInt(cursor.getColumnIndex(Weatherinfo.INFO_COUNTYID)));
                weatherinfo.setCity(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_CITY)));
                weatherinfo.setCityid(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_CITYID)));
                weatherinfo.setTemp1(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_TEMP1)));
                weatherinfo.setTemp2(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_TEMP2)));
                weatherinfo.setWeather(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_WEATHER)));
                weatherinfo.setPtime(cursor.getString(cursor.getColumnIndex(Weatherinfo.INFO_PTIME)));
            } while (cursor.moveToNext());
        }
        return weatherinfo ;
    }

}