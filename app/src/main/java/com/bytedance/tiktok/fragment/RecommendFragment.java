package com.bytedance.tiktok.fragment;

import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.adapter.VideoAdapter;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.view.FullScreenVideoView;
import com.bytedance.tiktok.view.viewpagerlayoutmanager.OnViewPagerListener;
import com.bytedance.tiktok.view.viewpagerlayoutmanager.ViewPagerLayoutManager;

import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-19
 * description 推荐播放页
 */
public class RecommendFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ArrayList<String> datas = new ArrayList<>();
    private VideoAdapter adapter;
    private ViewPagerLayoutManager viewPagerLayoutManager;
    /** 当前播放视频位置 */
    private int curPlayPos;
    private FullScreenVideoView videoView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {

        recyclerView = rootView.findViewById(R.id.recyclerview);
        adapter = new VideoAdapter(getActivity(), datas);
        recyclerView.setAdapter(adapter);

        videoView = new FullScreenVideoView(getActivity());

        setViewPagerLayoutManager();

        loadData();
    }

    private void setViewPagerLayoutManager() {
        viewPagerLayoutManager = new ViewPagerLayoutManager(getActivity());
        recyclerView.setLayoutManager(viewPagerLayoutManager);

        viewPagerLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                playCurVideo(curPlayPos);
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

    private void loadData() {
        for (int i=0;i<3;i++) {
            datas.add("");
        }
        adapter.notifyDataSetChanged();
    }

    private void playCurVideo(int position) {
        View itemView = viewPagerLayoutManager.findViewByPosition(position);
        if (itemView == null) return;

        ViewGroup rootView = itemView.findViewById(R.id.rl_container);
        curPlayPos = position;

        dettachParentView(rootView);

        String bgVideoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video_one;
        videoView.setVideoPath(bgVideoPath);
        videoView.start();
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));
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
        rootView.addView(videoView);
    }

}
