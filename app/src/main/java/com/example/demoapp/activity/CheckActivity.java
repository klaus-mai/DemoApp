package com.example.demoapp.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapp.R;
import com.example.demoapp.bmob.BBManager;
import com.google.android.material.button.MaterialButton;
import com.xuexiang.xui.widget.edittext.verify.VerifyCodeEditText;
import com.xuexiang.xui.widget.toast.XToast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class CheckActivity extends BaseActivity implements View.OnClickListener{

    private ImageView ivClose;
    private TextView tvPhone;
    private ImageView ivUp;
    private VerifyCodeEditText etCode;
    private MaterialButton btnSend;

    private String phone;
    private int count=60;
    private boolean isSend=true;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (isSend){
                if (msg.what==1){
                    btnSend.setEnabled(true);
                    btnSend.setText("重新发送");
                    btnSend.setBackgroundColor(ActivityCompat.getColor(CheckActivity.this, R.color.main_blue));
                    isSend=false;
                    count=60;
                } else {
                    count--;
                    btnSend.setText(count + "s");
                    handler.sendEmptyMessageDelayed(count,1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
    }

    @Override
    public void init() {

    }

    @Override
    public void initView() {
        ivClose = findViewById(R.id.iv_close_check);
        tvPhone = findViewById(R.id.tv_phone_check);
        ivUp = findViewById(R.id.iv_up_check);
        etCode = findViewById(R.id.et_code_check);
        btnSend = findViewById(R.id.btn_send_check);

    }

    @Override
    public void initData() {
        Intent intent=new Intent();
        phone=intent.getStringExtra("phone");
    }

    @Override
    public void initEvent() {
        etCode.setOnInputListener(new VerifyCodeEditText.OnInputListener() {
            @Override
            public void onComplete(String input) {

            }

            @Override
            public void onChange(String input) {

            }

            @Override
            public void onClear() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close_check:
            case R.id.iv_up_check:
                finish();
                break;
            case R.id.btn_send_check:
                sendCode();
                break;
        }
    }

    private void sendCode() {
        BBManager.getInstance().sendCode(phone, new QueryListener<Integer>() {
            @SuppressLint("CheckResult")
            @Override
            public void done(Integer integer, BmobException e) {
                if (e==null){
                    XToast.success(CheckActivity.this, "发送成功");
                } else {
                    XToast.success(CheckActivity.this, "失败   " + e.getErrorCode()).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.sendEmptyMessage(count);
    }
}