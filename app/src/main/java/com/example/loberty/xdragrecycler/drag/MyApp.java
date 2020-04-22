package com.example.loberty.xdragrecycler.drag;

import android.app.Application;

/**
 * Create by WangChen on 2020-04-22
 */
public class MyApp extends Application {

    private static MyApp indtance;

    public static MyApp getInstance(){
        return indtance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        indtance = this;
    }
}
