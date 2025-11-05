package com.example.healthapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;

import java.util.ArrayList;


public class RegisterActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword, mNickName;
    private DBManage dbManage;
    private RadioGroup radioGroup_main;

    private String sex = "男";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbManage = DBManage.getInstance(RegisterActivity.this);
        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mNickName = (EditText) findViewById(R.id.nickname);

        radioGroup_main = findViewById(R.id.radioGroup_main);
        radioGroup_main.check(R.id.radioButton1_main);
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton1_main) {
                    sex = "男";
                } else if (checkedId == R.id.radioButton2_main) {
                    sex = "女";
                }
            }
        });


        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = mUsername.getText().toString();
                String psd = mPassword.getText().toString();
                String nickname = mNickName.getText().toString();




                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(psd)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 添加密码长度验证（超过6位，即至少7位）
                if (psd.length() <= 6) {
                    Toast.makeText(RegisterActivity.this, "密码长度必须超过6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nickname)) {
                    Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                //存入数据库
                ArrayList<UserBean> userBeans = dbManage.selectUser(name);
                if (userBeans.size() > 0) {
                    Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                } else {
                    long l = dbManage.addUser(new UserBean(name, psd, sex, "", nickname));
                    if (l > 0) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
    }
}
