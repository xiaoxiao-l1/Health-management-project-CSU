package com.example.healthapp.fragment;

import android.app.AlertDialog;
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

import com.example.healthapp.R;
import com.example.healthapp.activity.LoginActivity;
import com.example.healthapp.activity.UserEditActivity;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.utils.SpUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MineFragment extends Fragment {

    private ImageView mHead;
    private UserBean user;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3, name;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = SpUtil.getInstance().getObject("user", UserBean.class);
        initView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        user = SpUtil.getInstance().getObject("user", UserBean.class);
        tv_1.setText("" + user.getNickname());
        tv_2.setText("" + user.getSex());
        tv_3.setText("" + user.getDes());
        name.setText("" + user.getNickname());

    }


    private void initView(View view) {
        tv_1 = view.findViewById(R.id.tv_1);
        tv_2 = view.findViewById(R.id.tv_2);
        tv_3 = view.findViewById(R.id.tv_3);
        name = view.findViewById(R.id.name);
        String string = SpUtil.getInstance().getString("selected_sports" + user.getId(), "");


        view.findViewById(R.id.tv_core).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserEditActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }






}
