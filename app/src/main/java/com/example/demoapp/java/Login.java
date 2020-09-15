package com.example.demoapp.java;

import com.example.demoapp.bmob.BBManager;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class Login {

    private LoginCallBack mLoginCallBack;
    private String phone;

    public void checkPhone(String phone,String code,LoginCallBack loginCallBack){
        this.mLoginCallBack=loginCallBack;
        this.phone=phone;

        BBManager.getInstance().checkPhoneCode(phone, code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    findUserByPhone();
                } else {
                    mLoginCallBack.error(e.getMessage(),e.getErrorCode());
                }
            }

        });
    }
    private void findUserByPhone(){

    }
    public interface LoginCallBack{
        void done(String id);

        void error(String msg,int errorCode);
    }
}
