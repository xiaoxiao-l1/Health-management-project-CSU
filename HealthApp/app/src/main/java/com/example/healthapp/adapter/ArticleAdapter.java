package com.example.healthapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.bean.ArticleBean;
import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder> {
    List<ArticleBean> list;


    public void setList(List<ArticleBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapterViewHolder holder, int position) {
        if (list != null) {
            ArticleBean article = list.get(position);
            holder.mName1.setText(article.getTitle());
            holder.mContent.setText(article.getContent());
            Integer articleImg = article.getImg();
            if (articleImg != null) {
                holder.mImg.setImageResource(articleImg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.itemClick(article);
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
        void itemClick(ArticleBean data);

    }

    class ArticleAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;
        private TextView mName1;
        private TextView mContent;

        public ArticleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mName1 = itemView.findViewById(R.id.name1);
            mContent = itemView.findViewById(R.id.content);
        }
    }
}
