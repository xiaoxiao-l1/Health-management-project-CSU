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
            Toast.makeText(this, "请输入血压值", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            // 血压可能是收缩压/舒张压（如 "120/80"），需特殊处理：分割后分别校验
            if (strTv_bloodpressure.contains("/")) {
                String[] pressureParts = strTv_bloodpressure.split("/");
                // 校验格式：必须是两个数值
                if (pressureParts.length != 2) {
                    Toast.makeText(this, "血压格式错误（请输入如 120/80 的格式）", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 分别校验收缩压和舒张压：非空 + 数字 + 非负数
                double systolic = Double.parseDouble(pressureParts[0].trim()); // 收缩压
                double diastolic = Double.parseDouble(pressureParts[1].trim()); // 舒张压
                if (systolic < 0 || diastolic < 0) {
                    Toast.makeText(this, "血压值不能为负数", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 可选：添加血压合理范围校验（符合生理逻辑，可选保留）
                if (systolic > 250 || diastolic > 150 || systolic < 60 || diastolic < 40) {
                    Toast.makeText(this, "血压值超出合理范围，请检查输入", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                // 若仅输入单个数值（如收缩压），直接校验非负数
                double pressure = Double.parseDouble(strTv_bloodpressure.trim());
                if (pressure < 0) {
                    Toast.makeText(this, "血压值不能为负数", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的血压值", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(strTv_bloodsugar)) {
            Toast.makeText(this, "请输入血糖值", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double bloodSugar = Double.parseDouble(strTv_bloodsugar);
            if (bloodSugar < 0) {
                Toast.makeText(this, "血糖值不能为负数", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的血糖值", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strTv_heartrate)) {
            Toast.makeText(this, "请输入心率值", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double heartRate = Double.parseDouble(strTv_heartrate);
            if (heartRate < 0) { // 核心：禁止负数输入
                Toast.makeText(this, "心率值不能为负数", Toast.LENGTH_SHORT).show();
                return;
            }
            // 生理合理范围校验（成年人静息心率60-100，运动时可达180+，范围放宽到0-200）
            if (heartRate > 200) {
                Toast.makeText(this, "心率值超出合理范围（0~200）", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的心率值", Toast.LENGTH_SHORT).show();
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
