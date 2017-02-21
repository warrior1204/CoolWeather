package com.example.administrator.coolweather;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.coolweather.model.Weatherinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {
    private Weatherinfo mWeatherinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        TextView city_name = (TextView) findViewById(R.id.city_name);
        TextView publish_text = (TextView) findViewById(R.id.publish_text);
        TextView current_date = (TextView) findViewById(R.id.current_date);
        TextView weather_desp = (TextView) findViewById(R.id.weather_desp);
        TextView temp1 = (TextView) findViewById(R.id.temp1);
        TextView temp2 = (TextView) findViewById(R.id.temp2);

        String current_time = gainCurrentTime();

        mWeatherinfo = (Weatherinfo) getIntent().getSerializableExtra(MainActivity.MWEATHERINFO);
        city_name.setText(mWeatherinfo.getCity());
        publish_text.setText("今天" + mWeatherinfo.getPtime() + "发布");
        current_date.setText(current_time);
        weather_desp.setText(mWeatherinfo.getWeather());
        temp1.setText(mWeatherinfo.getTemp1());
        temp2.setText(mWeatherinfo.getTemp2());
    }


    private String gainCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }
}
