package com.example.healthapp.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.adapter.FragmentTabAdapter;
import com.example.healthapp.fragment.HealthRecordFragment;
import com.example.healthapp.fragment.HomeFragment;
import com.example.healthapp.fragment.MineFragment;
import com.example.healthapp.fragment.ArticleFragment;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList< Fragment> fragments;
    private ArrayList< ViewGroup> buttons = new ArrayList<>();
    private FragmentTabAdapter tabAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListener();
    }

    private void initView() {
        findViewById(R.id.ll_tab_one).setOnClickListener(this);
        findViewById(R.id.ll_tab_two).setOnClickListener(this);
        findViewById(R.id.ll_tab_three).setOnClickListener(this);
        findViewById(R.id.ll_tab_fore).setOnClickListener(this);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HealthRecordFragment());
        fragments.add(new ArticleFragment());
        fragments.add(new MineFragment());
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fl_layout);
        buttons.add(findViewById(R.id.ll_tab_one));
        buttons.add(findViewById(R.id.ll_tab_two));
        buttons.add(findViewById(R.id.ll_tab_three));
        buttons.add(findViewById(R.id.ll_tab_fore));
        buttons.get(0).setSelected(true);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id ==R.id.ll_tab_one){
        tabAdapter.setCurrentFragment(0);
        }
        if(id ==R.id.ll_tab_two){
        tabAdapter.setCurrentFragment(1);
        }
        if(id ==R.id.ll_tab_three){
        tabAdapter.setCurrentFragment(2);
        }
        if(id ==R.id.ll_tab_fore){
        tabAdapter.setCurrentFragment(3);
        }
    }

    protected void initListener() {
        tabAdapter.setOnTabChangeListener(index -> {
            for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setSelected(i == index);
            }
        });
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
            }
        return super.onKeyDown(keyCode, event);
    }
}