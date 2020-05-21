package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.utils.OnVideoControllerListener;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class ControllerView extends RelativeLayout implements View.OnClickListener {
    private ImageView ivHead;
    private IconFontTextView ivLike;
    private IconFontTextView ivComment;
    private IconFontTextView ivShare;
    private OnVideoControllerListener listener;

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_controller, this);

        ivHead = rootView.findViewById(R.id.iv_head);
        ivLike = rootView.findViewById(R.id.iv_like);
        ivComment = rootView.findViewById(R.id.iv_comment);
        ivShare = rootView.findViewById(R.id.iv_share);

        ivHead.setOnClickListener(this);
        ivLike.setOnClickListener(this);
        ivComment.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    public void setListener(OnVideoControllerListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.iv_head:
                listener.onHeadClick();
                break;
            case R.id.iv_like:
                listener.onLikeClick();
                break;
            case R.id.iv_comment:
                listener.onCommentClick();
                break;
            case R.id.iv_share:
                listener.onShareClick();
                break;
        }
    }
}
