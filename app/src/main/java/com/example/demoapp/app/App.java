package com.example.demoapp.app;

import android.app.Application;

import com.xuexiang.xui.XUI;

import cn.bmob.v3.Bmob;

public class App extends Application {
    private String Key="";
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(  this,Key);
        XUI.init(this);
    }
}
