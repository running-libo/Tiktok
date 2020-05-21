package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.utils.OnVideoControllerListener;
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
        holder.controllerView.setListener(new OnVideoControllerListener() {
            @Override
            public void onHeadClick() {
                Toast.makeText(context, "点击头像", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLikeClick() {
                Toast.makeText(context, "点击点赞", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCommentClick() {
                Toast.makeText(context, "点击评论", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShareClick() {
                Toast.makeText(context, "点击分享", Toast.LENGTH_SHORT).show();
            }
        });
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
