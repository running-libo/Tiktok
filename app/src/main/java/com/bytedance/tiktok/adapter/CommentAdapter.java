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
public class CommentAdapter extends BaseRvAdapter<String, CommentAdapter.CommentViewHolder> {

    public CommentAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(CommentViewHolder holder, String data, int position) {

    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    public class CommentViewHolder extends BaseRvViewHolder {

        public CommentViewHolder(View itemView) {
            super(itemView);
        }
    }
}
