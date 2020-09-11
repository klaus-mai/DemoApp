package com.example.demoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

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
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        initView();
        initData();
        initData();
    }

    @Override
    public void init() {
        intent=new Intent();
    }

    @Override
    public void initView() {
        ivClose=findViewById(R.id.iv_close_login);
        ivSend=findViewById(R.id.iv_send_login);
        etPhone=findViewById(R.id.et_phone_login);
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
                   intent.putExtra(phone,"phone");

               } else {
                   XToast.warning(LoginActivity.this,"失败"+e.getErrorCode()).show();
               }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1000&&resultCode==RESULT_OK){
            intent=new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            //Log.e("LoginActivity", UserManager.get().getUser().getObjectId());
        }
    }
}