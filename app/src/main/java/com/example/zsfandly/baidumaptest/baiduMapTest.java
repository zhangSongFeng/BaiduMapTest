package com.example.zsfandly.baidumaptest;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by zsfandly on 2017/2/7.
 */

public class baiduMapTest extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
