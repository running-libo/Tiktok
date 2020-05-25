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
 * create on 2020-05-25
 * description
 */
public class ShareAdapter extends BaseRvAdapter<String, ShareAdapter.ShareViewHolder> {

    public ShareAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(ShareViewHolder holder, String data, int position) {

    }

    @NonNull
    @Override
    public ShareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_share, parent, false);
        return new ShareViewHolder(view);
    }

    public class ShareViewHolder extends BaseRvViewHolder {

        public ShareViewHolder(View itemView) {
            super(itemView);
        }
    }
}
