package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.PlayListActivity;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import java.util.List;

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
public class WorkAdapter extends BaseRvAdapter<Integer, WorkAdapter.WorkViewHolder> {

    public WorkAdapter(Context context, List<Integer> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(WorkViewHolder holder, Integer data, int position) {
        holder.ivCover.setImageResource(data);

        holder.itemView.setOnClickListener(v -> context.startActivity(new Intent(context, PlayListActivity.class)));
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rooView = LayoutInflater.from(context).inflate(R.layout.item_work, parent, false);
        return new WorkViewHolder(rooView);
    }

    public class WorkViewHolder extends BaseRvViewHolder {
        public ImageView ivCover;

        public WorkViewHolder(View itemView) {
            super(itemView);

            ivCover = itemView.findViewById(R.id.iv_cover);
        }
    }
}
