package com.example.healthapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.activity.ArticleDetailActivity;
import com.example.healthapp.adapter.ArticleAdapter;
import com.example.healthapp.bean.ArticleBean;
import com.example.healthapp.bean.UserBean;

import java.util.ArrayList;
import java.util.List;


public class ArticleFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private UserBean user;
    private ArticleAdapter articleAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleAdapter = new ArticleAdapter();
        mRecyclerView.setAdapter(articleAdapter);

        articleAdapter.setItemClick(new ArticleAdapter.ItemClick() {
            @Override
            public void itemClick(ArticleBean data) {
                Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<ArticleBean> articleBeans = new ArrayList<>();
        // 模拟5条健康相关文章数据
        articleBeans.add(new ArticleBean(1, R.drawable.article1, "健康饮食指南", 
            "均衡饮食是保持健康的基础。每天应摄入足够的蛋白质、碳水化合物、脂肪、维生素和矿物质。建议多吃新鲜蔬菜水果，选择全谷物而非精制谷物，适量摄入优质蛋白如鱼、禽、蛋和豆类。减少盐、糖和饱和脂肪的摄入量。每天喝足够的水，限制含糖饮料。注意饮食多样化，避免单一食物过量摄入。定时定量进餐，避免暴饮暴食。早餐要吃好，午餐要吃饱，晚餐要吃少。合理搭配食物，注意烹饪方式，少油少盐少糖。建立健康的饮食习惯需要长期坚持，从小培养良好的饮食行为对终身健康至关重要。",
            "2023-09-01"));
            
        articleBeans.add(new ArticleBean(2, R.drawable.article2, "每日运动计划", 
            "规律运动对身心健康都有显著益处。建议成年人每周至少进行150分钟中等强度有氧运动，或75分钟高强度有氧运动。运动形式可以多样化，如快走、跑步、游泳、骑自行车等。力量训练每周应进行2-3次，针对主要肌群。运动前要做好热身，运动后要做拉伸。根据个人体质和健康状况选择合适的运动强度和时间。循序渐进增加运动量，避免突然剧烈运动。办公室人群每小时应起身活动5分钟，减少久坐时间。老年人可选择太极拳、散步等低强度运动。儿童青少年每天应进行至少60分钟中高强度运动。运动时要穿着合适的服装和鞋子，注意补充水分。运动贵在坚持，找到自己喜欢的运动方式更容易长期维持。",
            "2023-09-02"));
            
        articleBeans.add(new ArticleBean(3, R.drawable.article3, "心理健康小贴士", 
            "心理健康与身体健康同等重要。日常生活中要学会管理压力，可以通过深呼吸、冥想、瑜伽等方式放松身心。保持规律的作息时间，保证充足睡眠。培养积极的思维方式，学会接纳不完美。建立良好的人际关系，与家人朋友保持联系。发展兴趣爱好，丰富精神生活。遇到困难时不要独自承受，可以寻求专业心理咨询帮助。学会表达情感，适当宣泄情绪。保持工作与生活的平衡，给自己留出休息时间。避免过度使用电子设备，多参与现实社交活动。培养感恩的心态，记录每天的小确幸。接受自己和他人的局限性，减少不必要的比较。心理健康的维护需要长期关注和投入，就像照顾身体一样重要。",
            "2023-09-03"));
            
        articleBeans.add(new ArticleBean(4, R.drawable.article4, "睡眠质量改善", 
            "良好的睡眠对健康至关重要。成年人每天需要7-9小时的睡眠时间。建立规律的睡眠时间表，尽量每天同一时间上床和起床。睡前1小时避免使用电子设备，蓝光会干扰褪黑激素分泌。卧室环境应安静、黑暗、凉爽，温度控制在18-22℃为宜。选择舒适的床垫和枕头。睡前避免摄入咖啡因、酒精和大量食物。建立放松的睡前仪式，如阅读、听轻音乐或泡温水澡。白天适当运动有助于夜间睡眠，但避免睡前3小时剧烈运动。午睡时间不宜过长，20-30分钟最佳。如果躺在床上20分钟仍无法入睡，应起床做些放松活动。长期睡眠问题及时就医，不要自行服用安眠药物。改善睡眠质量需要从生活习惯、环境等多方面入手，持之以恒才能见效。",
            "2023-09-04"));
            
        articleBeans.add(new ArticleBean(5, R.drawable.article5, "预防常见疾病", 
            "预防胜于治疗是健康管理的核心理念。定期体检可以早期发现健康问题。接种疫苗是预防传染病的有效手段。注意个人卫生，勤洗手，特别是在饭前便后。保持室内通风，减少病原体传播。均衡饮食和规律运动可以增强免疫力。控制体重在正常范围，避免肥胖相关疾病。戒烟限酒，减少慢性病风险。管理慢性病如高血压、糖尿病等，遵医嘱规律用药。注意季节变化，适时增减衣物预防感冒。外出时做好防晒，避免紫外线伤害。工作中注意劳逸结合，预防职业相关疾病。了解家族病史，有针对性地进行健康管理。学习基本急救知识，应对突发状况。健康的生活方式是预防疾病的基础，需要长期坚持才能见效。",
            "2023-09-05"));
        
        articleAdapter.setList(articleBeans);
    }











}
