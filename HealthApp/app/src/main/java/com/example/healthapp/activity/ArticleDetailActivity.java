package com.example.healthapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.bean.ArticleBean;


public class ArticleDetailActivity extends AppCompatActivity {

    private TextView mTitle, mContent;
    private ImageView mImg;
    private ArticleBean article;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 添加空值检查 ← 替换从这里开始
        if (getIntent() == null || getIntent().getSerializableExtra("data") == null) {
            finish();
            return;
        }

        article = (ArticleBean) getIntent().getSerializableExtra("data");

        // 检查 article 数据是否完整
        if (article == null) {
            finish();
            return;
        }

        // 初始化视图
        findViewById(R.id.finish).setOnClickListener(view -> finish());
        mTitle = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
        mImg = findViewById(R.id.img);

        // 设置数据
        initData();
    }

    private void initData() {
        mImg.setImageResource(article.getImg());
        mTitle.setText(article.getTitle());
        mContent.setText(article.getContent());
    }
}
