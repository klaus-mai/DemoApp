package com.example.demoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.demoapp.R;
import com.example.demoapp.bmob.BBManager;
import com.xuexiang.xui.widget.toast.XToast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivClose;
    private ImageView ivSend;
    private EditText etPhone;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        ivClose=findViewById(R.id.iv_close_login);
        ivSend=findViewById(R.id.iv_send_login);
        exPhone=findViewById(R.id.et_phone_login);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_close_login:
                finish();
                break;
            case R.id.iv_send_login:
                sendCode();
                break;
        }
    }

    private void sendCode() {
        phone=etPhone.getText().toString().trim();
        if (phone.length()!=11){
            XToast.warning(this,"手机号错误").show();
        }
        BBManager.getInstance().sendCode(phone, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
               if (e==null){
                   Intent intent=new Intent();
                   
               } else {
                   XToast.warning(LoginActivity.this,"失败"+e.getErrorCode()).show();
               }
            }
        });
    }
}