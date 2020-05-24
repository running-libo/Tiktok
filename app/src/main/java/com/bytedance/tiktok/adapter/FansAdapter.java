package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import java.util.List;

/**
 * create by libo
 * create on 2020-05-24
 * description
 */
public class FansAdapter extends BaseRvAdapter<String, FansAdapter.FansViewHolder> {

    public FansAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(FansViewHolder holder, String data, int position) {

    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fans, parent, false);
        return new FansViewHolder(view);
    }

    public class FansViewHolder extends BaseRvViewHolder {

        public FansViewHolder(View itemView) {
            super(itemView);
        }
    }
}
