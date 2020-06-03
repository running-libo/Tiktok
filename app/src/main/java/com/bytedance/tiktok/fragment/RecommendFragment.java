package com.bytedance.tiktok.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.MainActivity;
import com.bytedance.tiktok.adapter.VideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.bean.DataCreate;
import com.bytedance.tiktok.bean.MainPageChangeEvent;
import com.bytedance.tiktok.bean.PauseVideoEvent;
import com.bytedance.tiktok.bean.VideoBean;
import com.bytedance.tiktok.utils.OnVideoControllerListener;
import com.bytedance.tiktok.utils.RxBus;
import com.bytedance.tiktok.view.CommentDialog;
import com.bytedance.tiktok.view.ControllerView;
import com.bytedance.tiktok.view.FullScreenVideoView;
import com.bytedance.tiktok.view.LikeView;
import com.bytedance.tiktok.view.ShareDialog;
import com.bytedance.tiktok.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.bytedance.tiktok.view.viewpagerlayoutmanager.ViewPagerLayoutManager;
import java.util.ArrayList;
import butterknife.BindView;
import rx.functions.Action1;

/**
 * create by libo
 * create on 2020-05-19
 * description 推荐播放页
 */
public class RecommendFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private VideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /** 当前播放视频位置 */
    private int curPlayPos = -1;
    private FullScreenVideoView videoView;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {

        adapter = new VideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);

        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        setRefreshEvent();

        //监听播放或暂停事件
        RxBus.getDefault().toObservable(PauseVideoEvent.class)
            .subscribe((Action1<PauseVideoEvent>) event -> {
                if (event.isPlayOrPause()) {
                    videoView.start();
                } else {
                    videoView.pause();
                }
            });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (MainActivity.curMainPage == 0) {
            videoView.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        videoView.pause();
    }

    @Override
    public void onStop() {
        super.onStop();

        videoView.stopPlayback();
    }

    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playCurVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {

            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                playCurVideo(position);
            }
        });
    }

    private void setRefreshEvent() {
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }

    private void playCurVideo(int position) {
        if (position == curPlayPos) {
            return;
        }

        View itemView = viewPagerLayoutManager.findViewByPosition(position);
        if (itemView == null) {
            return;
        }

        ViewGroup rootView = itemView.findViewById(R.id.rl_container);
        LikeView likeView = rootView.findViewById(R.id.likeview);
        ControllerView controllerView = rootView.findViewById(R.id.controller);
        ImageView ivPlay = rootView.findViewById(R.id.iv_play);
        ivPlay.setAlpha(0.4f);

        //播放暂停事件
        likeView.setOnPlayPauseListener(() -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                ivPlay.setVisibility(View.VISIBLE);
            } else {
                videoView.start();
                ivPlay.setVisibility(View.GONE);
            }
        });

        likeShareEvent(controllerView);

        curPlayPos = position;

        dettachParentView(rootView);

        autoPlayVideo(curPlayPos);
    }

    /**
     * 移除videoview父view
     */
    private void dettachParentView(ViewGroup rootView) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        ViewGroup parent = (ViewGroup) videoView.getParent();
        if (parent != null) {
            parent.removeView(videoView);
        }
        rootView.addView(videoView, 0);
    }

    /**
     * 自动播放视频
     */
    private void autoPlayVideo(int position) {
        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + DataCreate.datas.get(position).getVideoRes();
        videoView.setVideoPath(bgVideoPath);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
    }

    /**
     * 用户操作事件
     */
    private void likeShareEvent(ControllerView controllerView) {
        controllerView.setListener(new OnVideoControllerListener() {
            @Override
            public void onHeadClick() {
                RxBus.getDefault().post(new MainPageChangeEvent(1));
            }

            @Override
            public void onLikeClick() {

            }

            @Override
            public void onCommentClick() {
                new CommentDialog().show(getChildFragmentManager(), "");
            }

            @Override
            public void onShareClick() {
                new ShareDialog().show(getChildFragmentManager(), "");
            }
        });
    }

}
