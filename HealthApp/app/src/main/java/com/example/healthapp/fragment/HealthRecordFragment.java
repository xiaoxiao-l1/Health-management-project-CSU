package com.example.healthapp.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.healthapp.R;
import com.example.healthapp.activity.HealthinfoAddActivity;
import com.example.healthapp.adapter.HealthinfoAdapter;
import com.example.healthapp.bean.Healthinfo;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;

import java.util.List;


public class HealthRecordFragment extends Fragment {

    private ImageView mFinish;
    private TextView mBar_right;
    private RecyclerView mRecyclerView;
    private HealthinfoAdapter mHealthinfoAdapter;
    private UserBean userBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_healthrecord, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userBean = SpUtil.getInstance().getObject("user", UserBean.class);
        initView(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        getList();
    }

    private void getList() {

        Healthinfo healthinfo = new Healthinfo();
        healthinfo.setUserid(Integer.parseInt(userBean.getId()));
        List<Healthinfo> query = DBManage.getInstance(getContext()).query(healthinfo);
        mHealthinfoAdapter.setList(query);
    }

    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHealthinfoAdapter = new HealthinfoAdapter();
        mRecyclerView.setAdapter(mHealthinfoAdapter);

        mBar_right = view.findViewById(R.id.bar_right);

        mBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HealthinfoAddActivity.class);
                startActivity(intent);
            }
        });

    }





}
