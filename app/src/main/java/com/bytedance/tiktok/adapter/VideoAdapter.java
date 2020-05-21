package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.view.ControllerView;
import com.bytedance.tiktok.view.LikeView;
import java.util.List;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class VideoAdapter extends BaseRvAdapter<String, VideoAdapter.VideoViewHolder> {

    public VideoAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(VideoViewHolder holder, String data, int position) {

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    public class VideoViewHolder extends BaseRvViewHolder {
        public LikeView likeView;
        public ControllerView controllerView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            likeView = itemView.findViewById(R.id.likeview);
            controllerView = itemView.findViewById(R.id.controller);
        }
    }
}
