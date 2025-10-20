package com.example.healthapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.bean.Healthinfo;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;
import com.example.healthapp.utils.TimeUtils;


public class HealthinfoAddActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFinish;
    private TextView mBar_right;
    private EditText mTv_bloodpressure;
    private EditText mTv_bloodsugar;
    private EditText mTv_heartrate;
    private EditText mTv_des;
    private Button mCommit;
    private UserBean user;
    private String mType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addhealthinfo);
        user = SpUtil.getInstance().getObject("user", UserBean.class);
        initView();

    }

    private void initView() {

        mFinish = findViewById(R.id.finish);
        mBar_right = findViewById(R.id.bar_right);
        mTv_bloodpressure = findViewById(R.id.tv_bloodpressure);
        mTv_bloodsugar = findViewById(R.id.tv_bloodsugar);
        mTv_heartrate = findViewById(R.id.tv_heartrate);
        mTv_des = findViewById(R.id.tv_des);
        mCommit = findViewById(R.id.commit);
        mFinish.setOnClickListener(this);
        mCommit.setOnClickListener(this);

    }

    private void commit() {

        String strTv_bloodpressure = mTv_bloodpressure.getText().toString();
        String strTv_bloodsugar = mTv_bloodsugar.getText().toString();
        String strTv_heartrate = mTv_heartrate.getText().toString();
        String strTv_des = mTv_des.getText().toString();
        if (TextUtils.isEmpty(strTv_bloodpressure)) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strTv_bloodsugar)) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strTv_heartrate)) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strTv_des)) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }
        Healthinfo data = new Healthinfo();
        data.setBloodpressure(strTv_bloodpressure);
        data.setBloodsugar(strTv_bloodsugar);
        data.setHeartrate(strTv_heartrate);
        data.setDes(strTv_des);
        data.setUserid(Integer.parseInt(user.getId()));
        data.setUsername(user.getName());
        data.setTime(TimeUtils.getCurrentTime());

        DBManage.getInstance(HealthinfoAddActivity.this).insert(data);
        finish();
        Toast.makeText(HealthinfoAddActivity.this, "提交完成", Toast.LENGTH_SHORT).show();


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
