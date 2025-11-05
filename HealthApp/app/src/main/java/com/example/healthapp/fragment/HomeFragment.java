package com.example.healthapp.fragment;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.healthapp.R;
import com.example.healthapp.adapter.HealthinfoAdapter;
import com.example.healthapp.bean.Healthinfo;
import com.example.healthapp.bean.UserBean;
import com.example.healthapp.db.DBManage;
import com.example.healthapp.utils.SpUtil;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {





    private BarChart mBarChart;

    private Banner mBanner;

    private UserBean user;
    private RecyclerView mRecyclerView;


    private List<String> recommendations;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView tv_integral;
    private int integral;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = SpUtil.getInstance().getObject("user", UserBean.class);
        initView(view);

        initChart();


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View view) {

        mBanner = view.findViewById(R.id.banner);
        ArrayList<String> banners = new ArrayList<>();
        banners.add(String.valueOf(R.mipmap.banner1));
        banners.add(String.valueOf(R.mipmap.banner2));
        mBanner.setAdapter(new BannerImageAdapter<String>(banners) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        //图片加载自己实现
                        Glide.with(holder.itemView)
                                .load(BitmapFactory.decodeResource(getResources(), Integer.parseInt(data)))
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()));


        mBarChart = view.findViewById(R.id.bar_chart);


        tv_integral = view.findViewById(R.id.tv_integral);

        integral = SpUtil.getInstance().getInt(user.getId(), 0);
        tv_integral.setText("当前积分：" + integral);
        Button btn1 = view.findViewById(R.id.btn1);
        btn1
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理按钮1的点击事件
                new AlertDialog.Builder(getContext())
                        .setTitle("确认打卡")
                        .setMessage("是否确认打卡？")
                        .setPositiveButton("确认", (dialog, which) -> {
                            integral += 10;
                            tv_integral.setText("当前积分：" + integral);
                            SpUtil.getInstance().putInt(user.getId(), integral);
                            Toast.makeText(getContext(), "已完成今日运动量", Toast.LENGTH_SHORT).show();
                            btn1.setVisibility(View.GONE);
                        })
                        .setNegativeButton("取消", null) // 点击取消按钮不执行任何操作
                        .show();


            }
        });

        Button btn2 = view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理按钮2的点击事件
                new AlertDialog.Builder(getContext())
                        .setTitle("确认打卡")
                        .setMessage("是否确认打卡？")
                        .setPositiveButton("确认", (dialog, which) -> {
                            integral += 10;
                            tv_integral.setText("当前积分：" + integral);
                            SpUtil.getInstance().putInt(user.getId(), integral);
                            Toast.makeText(getContext(), "已完成今日饮食规划", Toast.LENGTH_SHORT).show();
                            btn2.setVisibility(View.GONE);
                        })
                        .setNegativeButton("取消", null) // 点击取消按钮不执行任何操作
                        .show();


            }
        });

    }

    private void initChart() {

        mBarChart.setTouchEnabled(false);
        mBarChart.setScaleEnabled(false); //是否支持缩放，默认true
        //设置图表坐标
        setAxis();

    }


    private void setAxis() {
        // 设置x轴
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        //   xAxis.setLabelCount(5);  // 设置x轴上的标签个数
        xAxis.setTextSize(10f); // x轴上标签的大小
        // 设置x轴显示的值的格式
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                SelfStudyRoom selfStudyRoom = selfStudyRooms.get((int) value);
//                String time = selfStudyRoom.getTime();
//                return TimeUtils.getYYYMMDD(time);
//            }
//        });
        xAxis.setYOffset(15); // 设置标签对x轴的偏移量，垂直方向
        xAxis.setLabelRotationAngle(-45);

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = mBarChart.getAxisRight();
        //yAxis_right.setAxisMaximum(1200f);  // 设置y轴的最大值
        yAxis_right.setAxisMinimum(0);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = mBarChart.getAxisLeft();
        //  yAxis_left.setAxisMaximum(1200f);
        yAxis_left.setAxisMinimum(0);
        yAxis_left.setTextSize(15f); // 设置y轴的标签大小

        yAxis_left.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int)value);
            }
        });


    }


    private void initData() {

        Healthinfo healthinfo = new Healthinfo();
        healthinfo.setUserid(Integer.parseInt(user.getId()));
        List<Healthinfo> query = DBManage.getInstance(getContext()).query(healthinfo);



        ArrayList<String> list = new ArrayList<>();


        setData(query);
    }


    private void setData(List<Healthinfo> list){
        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Float uestime = Float.parseFloat(list.get(i).getBloodsugar());
            barEntries1.add(new BarEntry(i, uestime));
        }

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "统计表");
        barDataSet1.setValueTextColor(Color.RED); // 值的颜色
        barDataSet1.setValueTextSize(12f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#1AE61A")); // 柱子的颜色
        //   barDataSet1.setLabel(""); // 设置标签之后，图例的内容默认会以设置的标签显示

        sets.add(barDataSet1);

        BarData barData = new BarData(sets);
        barData.setBarWidth(0.3f); // 设置柱子的宽度
        mBarChart.setData(barData);
        mBarChart.invalidate(); // 刷新
    }












}
