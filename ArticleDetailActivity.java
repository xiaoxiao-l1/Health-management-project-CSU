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
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        article = (ArticleBean) getIntent().getSerializableExtra("data");
        findViewById(R.id.finish).setOnClickListener(view -> finish());
        mTitle = findViewById(R.id.title);
        mContent = findViewById(R.id.content);
        mImg = findViewById(R.id.img);


        mImg.setImageResource(article.getImg());
        mTitle.setText(article.getTitle());
        mContent.setText(article.getContent());

    }
}
