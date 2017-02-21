package com.example.administrator.coolweather.util;

import android.text.TextUtils;

import com.example.administrator.coolweather.db.CoolWeatherDB;
import com.example.administrator.coolweather.model.City;
import com.example.administrator.coolweather.model.County;
import com.example.administrator.coolweather.model.Province;
import com.example.administrator.coolweather.model.Weatherinfo;

import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(
                     CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length > 0) {
                for (String p : allProvinces) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
// 将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
                                               String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
// 将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
                          String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
// 将解析出来的数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }




    /**
     * 解析和处理服务器返回的json天气数据，并将解析的数据存到本地
     */
    public static boolean handleWeatherResponse(
                             CoolWeatherDB coolWeatherDB, String response,int countyId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject weatherinfo = jsonObject.getJSONObject("weatherinfo");
                String city = weatherinfo.getString(Weatherinfo.INFO_CITY);
                String cityid = weatherinfo.getString(Weatherinfo.INFO_CITYID);
                String temp1 = weatherinfo.getString(Weatherinfo.INFO_TEMP1);
                String temp2 = weatherinfo.getString(Weatherinfo.INFO_TEMP2);
                String weather = weatherinfo.getString(Weatherinfo.INFO_WEATHER);
                String ptime = weatherinfo.getString(Weatherinfo.INFO_PTIME);
                Weatherinfo weatherDb = new Weatherinfo();
                weatherDb.setCountyId(countyId);
                weatherDb.setCity(city);
                weatherDb.setCityid(cityid);
                weatherDb.setTemp1(temp1);
                weatherDb.setTemp2(temp2);
                weatherDb.setWeather(weather);
                weatherDb.setPtime(ptime);
                coolWeatherDB.saveWeather(weatherDb);
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}