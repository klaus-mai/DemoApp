package com.example.demoapp.bmob;

import java.nio.ByteBuffer;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.listener.QueryListener;

public class BBManager {
    public static BBManager bbManager;
    private BBManager(){}
    public static BBManager getInstance(){
        if (bbManager==null){
            synchronized (BBManager.class){
                if (bbManager==null){
                    bbManager=new BBManager();
                }
            }
        }
        return bbManager;
    }
    public void sendCode(String phone, QueryListener<Integer> listener){
        BmobSMS.requestSMSCode(phone,"",listener);
    }
}
