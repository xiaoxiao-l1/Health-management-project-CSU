package com.example.healthapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;


public class UserEditActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFinish;
    private EditText mTv_username;

    private Button mCommit;

    private UserBean user;
    private EditText et_1;
    private EditText et_2;
    private EditText et_3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        user = SpUtil.getInstance().getObject("user", UserBean.class);
        initView();

    }

    private void initView() {

        mFinish = findViewById(R.id.finish);

        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);



        mCommit = findViewById(R.id.commit);
        mFinish.setOnClickListener(this);
        mCommit.setOnClickListener(this);

        if (user != null) {
            String realname = user.getNickname();
            if (!TextUtils.isEmpty(realname)){
                et_1.setText(""+realname);
            }
            if (!TextUtils.isEmpty(user.getSex())){
                et_2.setText(""+user.getSex());
            }
            if (!TextUtils.isEmpty(user.getDes())){
                et_3.setText(""+user.getDes());
            }

        }

    }

    private void commit() {

        String strTv_nickname = et_1.getText().toString();
        String strTv_phone = et_2.getText().toString();
        String strTv_des = et_3.getText().toString();


        user.setNickname(strTv_nickname);
        user.setSex(strTv_phone);
        user.setDes(strTv_des);
        SpUtil.getInstance().putObject("user", user);
        DBManage.getInstance(this).updateUser(user);
        finish();
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();



    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.finish) {
            finish();

        }
        if (id == R.id.commit) {
            commit();

        }

    }
}
