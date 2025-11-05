package com.example.healthapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.healthapp.R;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private DBManage dbManage;


    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManage = DBManage.getInstance(LoginActivity.this);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);




        Button mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = mUsername.getText().toString();
                String strPassword = mPassword.getText().toString();



                if (TextUtils.isEmpty(strUsername)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strPassword)){
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }


                ArrayList<UserBean> userBeans = dbManage.selectUser(strUsername);
                if (userBeans.size()>0){
                    UserBean userBean = userBeans.get(0);

                    if (strPassword.equals(userBean.getPsw())){
                        SpUtil.getInstance().putObject("user",userBean);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(LoginActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }





            }
        });

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}
