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

    private ArrayList<Fragment> fragments;
    private ArrayList<ViewGroup> buttons = new ArrayList<>();
    private FragmentTabAdapter tabAdapter;
    private long exitTime = 0;

    // Tab索引常量
    private static final int TAB_HOME = 0;
    private static final int TAB_HEALTH_RECORD = 1;
    private static final int TAB_ARTICLE = 2;
    private static final int TAB_MINE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListener();
    }

    private void initView() {
        // 初始化Tab点击监听
        initTabClickListeners();

        // 初始化Fragment列表
        initFragments();

        // 初始化Tab适配器
        initTabAdapter();

        // 设置默认选中第一个Tab
        setDefaultSelectedTab();
    }

    private void initTabClickListeners() {
        findViewById(R.id.ll_tab_one).setOnClickListener(this);
        findViewById(R.id.ll_tab_two).setOnClickListener(this);
        findViewById(R.id.ll_tab_three).setOnClickListener(this);
        findViewById(R.id.ll_tab_fore).setOnClickListener(this);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HealthRecordFragment());
        fragments.add(new ArticleFragment());
        fragments.add(new MineFragment());
    }

    private void initTabAdapter() {
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fl_layout);

        // 初始化按钮列表
        buttons.add((ViewGroup) findViewById(R.id.ll_tab_one));
        buttons.add((ViewGroup) findViewById(R.id.ll_tab_two));
        buttons.add((ViewGroup) findViewById(R.id.ll_tab_three));
        buttons.add((ViewGroup) findViewById(R.id.ll_tab_fore));
    }

    private void setDefaultSelectedTab() {
        if (!buttons.isEmpty()) {
            buttons.get(TAB_HOME).setSelected(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        int tabIndex = -1;

        if (id == R.id.ll_tab_one) {
            tabIndex = TAB_HOME;
        } else if (id == R.id.ll_tab_two) {
            tabIndex = TAB_HEALTH_RECORD;
        } else if (id == R.id.ll_tab_three) {
            tabIndex = TAB_ARTICLE;
        } else if (id == R.id.ll_tab_fore) {
            tabIndex = TAB_MINE;
        }

        if (tabIndex != -1) {
            tabAdapter.setCurrentFragment(tabIndex);
        }
    }

    protected void initListener() {
        tabAdapter.setOnTabChangeListener(index -> {
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setSelected(i == index);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isDoubleClickExit()) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isDoubleClickExit() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = currentTime;
            return false;
        } else {
            return true;
        }
    }
}