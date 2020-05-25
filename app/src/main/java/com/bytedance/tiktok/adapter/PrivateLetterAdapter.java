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
public class PrivateLetterAdapter extends BaseRvAdapter<String, PrivateLetterAdapter.PrivateLetterViewHolder> {

    public PrivateLetterAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(PrivateLetterViewHolder holder, String data, int position) {

    }

    @NonNull
    @Override
    public PrivateLetterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_private_letter,parent, false);
        return new PrivateLetterViewHolder(view);
    }

    public class PrivateLetterViewHolder extends BaseRvViewHolder {

        public PrivateLetterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
