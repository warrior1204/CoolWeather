package com.example.administrator.coolweather.model;


import java.io.Serializable;

public class Weatherinfo implements Serializable {
    private int id;
    private int countyId;
    private String city;
    private String cityid;
    private String temp1;
    private String temp2;
    private String weather;
    private String ptime;

    public static final String TABLE_NAME = "Weatherinfo";
    public static final String INFO_COUNTYID = "countyId";
    public static final String INFO_CITY = "city";
    public static final String INFO_CITYID = "cityid";
    public static final String INFO_TEMP1 = "temp1";
    public static final String INFO_TEMP2 = "temp2";
    public static final String INFO_WEATHER = "weather";
    public static final String INFO_PTIME = "ptime";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }
}
