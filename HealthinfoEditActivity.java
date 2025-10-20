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
import com.example.healthapp.db.DBManage;


public class HealthinfoEditActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mFinish;
    private TextView mBar_right;
    private EditText mTv_bloodpressure;
    private EditText mTv_bloodsugar;
    private EditText mTv_heartrate;
    private EditText mTv_des;
    private Button mCommit;
    private Button mDel;
    private Healthinfo healthinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithealthinfo);
        healthinfo = (Healthinfo) getIntent().getSerializableExtra("data");

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
        mDel = findViewById(R.id.del);
        mFinish.setOnClickListener(this);
        mCommit.setOnClickListener(this);
        mDel.setOnClickListener(this);
        mTv_bloodpressure.setText(healthinfo.getBloodpressure());
        mTv_bloodsugar.setText(healthinfo.getBloodsugar());
        mTv_heartrate.setText(healthinfo.getHeartrate());
        mTv_des.setText(healthinfo.getDes());


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
        healthinfo.setBloodpressure(strTv_bloodpressure);
        healthinfo.setBloodsugar(strTv_bloodsugar);
        healthinfo.setHeartrate(strTv_heartrate);
        healthinfo.setDes(strTv_des);

        Healthinfo healthinfo1 = new Healthinfo();
        healthinfo1.setId(healthinfo.getId());
        DBManage.getInstance(HealthinfoEditActivity.this).update(healthinfo,healthinfo1);
        finish();
        Toast.makeText(HealthinfoEditActivity.this, "提交完成", Toast.LENGTH_SHORT).show();


    }

    private void del() {

        Healthinfo healthinfo1 = new Healthinfo();
        healthinfo1.setId(healthinfo.getId());
        DBManage.getInstance(HealthinfoEditActivity.this).delete(healthinfo1);
        finish();
        Toast.makeText(HealthinfoEditActivity.this, "删除完成", Toast.LENGTH_SHORT).show();


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
        if (id == R.id.del) {
            del();

        }
    }
}
