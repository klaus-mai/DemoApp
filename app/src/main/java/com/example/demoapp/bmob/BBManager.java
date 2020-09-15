package com.example.demoapp.bmob;

import java.nio.ByteBuffer;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

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
    /**
     * * 验证验证码
     *      *
     *      * @param phone    手机号
     *      * @param code     验证码
     *      * @param listener 结果回调
     */
    public void checkPhoneCode(String phone, String code, UpdateListener listener){
        BmobSMS.verifySmsCode(phone,code,listener);
    }
}
