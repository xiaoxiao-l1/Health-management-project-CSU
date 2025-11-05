package com.example.healthapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.activity.HealthinfoEditActivity;
import com.example.healthapp.bean.Healthinfo;

import java.util.List;


public class HealthinfoAdapter extends RecyclerView.Adapter<HealthinfoAdapter.HealthinfoAdapterViewHolder> {
    List<Healthinfo> list;


    public void setList(List<Healthinfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HealthinfoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HealthinfoAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_healthinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HealthinfoAdapterViewHolder holder, int position) {
        if (list != null) {
            Healthinfo healthinfo = list.get(position);
            holder.mTv_bloodpressure.setText("血压："+ healthinfo.getBloodpressure());
            holder.mTv_bloodsugar.setText("血糖："+ healthinfo.getBloodsugar());
            holder.mTv_heartrate.setText("心率："+ healthinfo.getHeartrate());
            holder.mTv_des.setText("健康状态备注："+ healthinfo.getDes());
            holder.mTv_time.setText(healthinfo.getTime());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), HealthinfoEditActivity.class);
                    intent.putExtra("data",healthinfo);
                    v.getContext().startActivity(intent);

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface ItemClick {
        void itemClick(Healthinfo data, int pos);
    }

    class HealthinfoAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv_bloodpressure;
        private TextView mTv_bloodsugar;
        private TextView mTv_heartrate;
        private TextView mTv_des,mTv_time;

        public HealthinfoAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv_bloodpressure = itemView.findViewById(R.id.tv_bloodpressure);
            mTv_bloodsugar = itemView.findViewById(R.id.tv_bloodsugar);
            mTv_heartrate = itemView.findViewById(R.id.tv_heartrate);
            mTv_des = itemView.findViewById(R.id.tv_des);
            mTv_time = itemView.findViewById(R.id.tv_time);

        }
    }
}
