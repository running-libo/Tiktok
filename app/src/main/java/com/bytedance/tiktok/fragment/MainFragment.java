package com.bytedance.tiktok.fragment;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseFragment;
import com.bytedance.tiktok.base.CommPagerAdapter;
import java.util.ArrayList;

/**
 * create by libo
 * create on 2020-05-19
 * description 主页fragment
 */
public class MainFragment extends BaseFragment {
    private CurrentLocationFragment currentLocationFragment;
    private RecommendFragment recommendFragment;
    private RecommendFragment focusFragment;
    private ViewPager viewPager;
    private XTabLayout tabLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private CommPagerAdapter pagerAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        viewPager = rootView.findViewById(R.id.viewpager);
        tabLayout = rootView.findViewById(R.id.tablayout);

        currentLocationFragment = new CurrentLocationFragment();
//        focusFragment = new RecommendFragment();
        recommendFragment = new RecommendFragment();
        fragments.add(currentLocationFragment);
//        fragments.add(focusFragment);
        fragments.add(recommendFragment);

        tabLayout.addTab(tabLayout.newTab().setText("海淀"));
//        tabLayout.addTab(tabLayout.newTab().setText("关注"));
        tabLayout.addTab(tabLayout.newTab().setText("推荐"));

        pagerAdapter = new CommPagerAdapter(getChildFragmentManager(), fragments, new String[] {"海淀", "推荐"});
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(1).select();
    }

}
