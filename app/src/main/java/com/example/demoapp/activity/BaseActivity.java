package com.example.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;

import com.example.demoapp.R;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        XUI.initTheme(this);
        StatusBarUtils.initStatusBarStyle(this,false, ActivityCompat.getColor(this,R.color.main_blue));
    }
    public abstract void init();
    public abstract void initView();
    public abstract void initData();
    public abstract void initEvent();
}