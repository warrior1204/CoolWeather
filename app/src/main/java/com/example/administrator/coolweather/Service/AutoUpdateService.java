package com.example.administrator.coolweather.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.coolweather.MainActivity;
import com.example.administrator.coolweather.db.CoolWeatherDB;
import com.example.administrator.coolweather.receiver.AlarmReceiver;
import com.example.administrator.coolweather.util.HttpCallbackListener;
import com.example.administrator.coolweather.util.HttpUtil;
import com.example.administrator.coolweather.util.Utility;

import java.util.Date;


public class AutoUpdateService extends Service {
    private String mWeatherCode;
    private CoolWeatherDB mcoolWeatherDb;
    private int mcountyId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2017/2/19 将所点击的县的天气信息更新并存到数据库中
                Log.d("TAG", mWeatherCode+"executed at " + new Date().toString());
                updateWeather();
            }

            private void updateWeather() {
                mWeatherCode = MainActivity.mWeatherCode;
                mcoolWeatherDb = MainActivity.coolWeatherDB;
                mcountyId = MainActivity.selectedCountId;

                String address;
                address = "http://www.weather.com.cn/data/cityinfo/" + mWeatherCode + ".html";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        boolean result = false;
                        result = Utility.handleWeatherResponse(mcoolWeatherDb, response, mcountyId);
                    }
                    @Override
                    public void onError(Exception e) {
                    }
                });

            }

        }).start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int hours = 20* 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + hours;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
