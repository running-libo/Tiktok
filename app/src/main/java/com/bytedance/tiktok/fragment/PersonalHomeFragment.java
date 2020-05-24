package com.bytedance.tiktok.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.activity.FocusActivity;
import com.bytedance.tiktok.activity.ShowImageActivity;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-19
 * description 个人主页fragment
 */
public class PersonalHomeFragment extends BaseFragment implements View.OnClickListener {
    private XTabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TextView tvTitle;
    private TextView tvFocus;
    private ImageView ivHead;
    private ImageView ivBg;
    private LinearLayout llFocus, llFans;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;
    private String[] titles = new String[] {"作品 128", "动态 128", "喜欢 802"};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_personal_home;
    }

    @Override
    protected void init() {
        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tablayout);
        toolbar = rootView.findViewById(R.id.toolbar);
        appBarLayout = rootView.findViewById(R.id.appbarlayout);
        tvTitle = rootView.findViewById(R.id.tv_title);
        tvFocus = rootView.findViewById(R.id.tv_focus);
        ivHead = rootView.findViewById(R.id.iv_head);
        ivBg = rootView.findViewById(R.id.iv_bg);
        llFocus = rootView.findViewById(R.id.ll_focus);
        llFans = rootView.findViewById(R.id.ll_fans);

        //解决toolbar左边距问题
        toolbar.setContentInsetsAbsolute(0, 0);

        for (int i=0;i<titles.length;i++) {
            fragments.add(new WorkFragment());
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]));
        }

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setAppbarLayoutPercent();

        ivHead.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        llFocus.setOnClickListener(this);
        llFans.setOnClickListener(this);
    }

    /**
     * 过渡动画跳转页面
     * @param view
     */
    public void transitionAnim(View view){
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getString(R.string.trans));
        ActivityCompat.startActivity(getActivity(), new Intent(getActivity(), ShowImageActivity.class), compat.toBundle());
    }

    /**
     * 根据滚动比例渐变view
     */
    private void setAppbarLayoutPercent() {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = (Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange());  //滑动比例

            if (percent > 0.8) {
                tvTitle.setVisibility(View.VISIBLE);
                tvFocus.setVisibility(View.VISIBLE);

                float alpha = 1- (1-percent)*5;  //渐变变换
                tvTitle.setAlpha(alpha);
                tvFocus.setAlpha(alpha);
            } else {
                tvTitle.setVisibility(View.GONE);
                tvFocus.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                transitionAnim(ivHead);
                break;
            case R.id.iv_bg:
                transitionAnim(ivBg);
                break;
            case R.id.ll_focus:
                startActivity(new Intent(getActivity(), FocusActivity.class));
                break;
            case R.id.ll_fans:
                startActivity(new Intent(getActivity(), FocusActivity.class));
                break;
        }
    }
}
