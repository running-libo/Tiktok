package com.bytedance.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseRvAdapter;
import com.bytedance.tiktok.base.BaseRvViewHolder;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * create by libo
 * create on 2020-05-24
 * description
 */
public class FansAdapter extends BaseRvAdapter<VideoBean.UserBean, FansAdapter.FansViewHolder> {

    public FansAdapter(Context context, List<VideoBean.UserBean> datas) {
        super(context, (ArrayList<VideoBean.UserBean>) datas);
    }

    @Override
    protected void onBindData(FansViewHolder holder, VideoBean.UserBean userBean, int position) {
        holder.ivHead.setImageResource(userBean.getHead());
        holder.tvNickname.setText(userBean.getNickName());
        holder.tvFocus.setText(userBean.isFocused() ? "已关注" : "关注");

        holder.tvFocus.setOnClickListener(v -> {
            if (!userBean.isFocused()) {
                holder.tvFocus.setText("已关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite);
            } else {
                holder.tvFocus.setText("关注");
                holder.tvFocus.setBackgroundResource(R.drawable.shape_round_red);
            }

            userBean.setFocused(!userBean.isFocused());
        });
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_fans, parent, false);
        return new FansViewHolder(view);
    }

    public class FansViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_head)
        CircleImageView ivHead;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_focus)
        TextView tvFocus;

        public FansViewHolder(View itemView) {
            super(itemView);
        }
    }
}
