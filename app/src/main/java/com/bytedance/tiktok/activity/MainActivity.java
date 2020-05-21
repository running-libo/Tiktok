package com.bytedance.tiktok.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.fragment.MainFragment;
import com.bytedance.tiktok.fragment.PersonalHomeFragment;
import java.util.ArrayList;

/**
 * create by libo
 * create on 2020/5/19
 * description 主页面
 */
public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        viewPager = findViewById(R.id.viewpager);
        fragments.add(new MainFragment());
        fragments.add(new PersonalHomeFragment());
        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"",""});
        viewPager.setAdapter(pagerAdapter);
    }
}
