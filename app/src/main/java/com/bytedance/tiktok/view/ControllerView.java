package com.bytedance.tiktok.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.utils.AutoLinkHerfManager;
import com.bytedance.tiktok.utils.NumUtils;
import com.bytedance.tiktok.utils.OnVideoControllerListener;
import com.bytedance.tiktok.utils.autolinktextview.AutoLinkTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import static android.view.animation.Animation.INFINITE;

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
public class ControllerView extends RelativeLayout implements View.OnClickListener {
    @BindView(R.id.tv_content)
    AutoLinkTextView autoLinkTextView;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.lottie_anim)
    LottieAnimationView animationView;
    @BindView(R.id.rl_like)
    RelativeLayout rlLike;
    @BindView(R.id.iv_comment)
    IconFontTextView ivComment;
    @BindView(R.id.iv_share)
    IconFontTextView ivShare;
    @BindView(R.id.iv_record)
    ImageView ivRecord;
    @BindView(R.id.rl_record)
    RelativeLayout rlRecord;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.iv_head_anim)
    CircleImageView ivHeadAnim;
    @BindView(R.id.iv_like)
    IconFontTextView ivLike;
    @BindView(R.id.tv_likecount)
    TextView tvLikecount;
    @BindView(R.id.tv_commentcount)
    TextView tvCommentcount;
    @BindView(R.id.tv_sharecount)
    TextView tvSharecount;
    @BindView(R.id.iv_focus)
    ImageView ivFocus;
    private OnVideoControllerListener listener;
    private VideoBean videoData;

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_controller, this);
        ButterKnife.bind(this, rootView);

        ivHead.setOnClickListener(this);
        ivComment.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        rlLike.setOnClickListener(this);
        ivFocus.setOnClickListener(this);

        setRotateAnim();
    }

    public void setVideoData(VideoBean videoData) {
        this.videoData = videoData;

        ivHead.setImageResource(videoData.getUserBean().getHead());
        tvNickname.setText(videoData.getUserBean().getNickName());
        AutoLinkHerfManager.setContent(videoData.getContent(), autoLinkTextView);
        ivHeadAnim.setImageResource(videoData.getUserBean().getHead());
        tvLikecount.setText(NumUtils.numberFilter(videoData.getLikeCount()));
        tvCommentcount.setText(NumUtils.numberFilter(videoData.getCommentCount()));
        tvSharecount.setText(NumUtils.numberFilter(videoData.getShareCount()));

        animationView.setAnimation("like.json");

        //点赞状态
        if (videoData.isLiked()) {
            ivLike.setTextColor(getResources().getColor(R.color.color_FF0041));
        } else {
            ivLike.setTextColor(getResources().getColor(R.color.white));
        }

        //关注状态
        if (videoData.isFocused()) {
            ivFocus.setVisibility(GONE);
        } else {
            ivFocus.setVisibility(VISIBLE);
        }
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
            case R.id.rl_like:
                listener.onLikeClick();

                if (!videoData.isLiked()) {
                    //点赞
                    animationView.setVisibility(VISIBLE);
                    animationView.playAnimation();
                    ivLike.setTextColor(getResources().getColor(R.color.color_FF0041));
                } else {
                    //取消点赞
                    animationView.setVisibility(INVISIBLE);
                    ivLike.setTextColor(getResources().getColor(R.color.white));
                }

                videoData.setLiked(!videoData.isLiked());

                break;
            case R.id.iv_comment:
                listener.onCommentClick();
                break;
            case R.id.iv_share:
                listener.onShareClick();
                break;
            case R.id.iv_focus:
                if (!videoData.isFocused()) {
                    videoData.setLiked(true);
                    ivFocus.setVisibility(GONE);
                }
                break;
        }
    }

    /**
     * 循环旋转动画
     */
    private void setRotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(INFINITE);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        ivRecord.startAnimation(rotateAnimation);
    }
}
