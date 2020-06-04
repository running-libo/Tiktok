package com.bytedance.tiktok.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bytedance.tiktok.R;
import com.bytedance.tiktok.base.BaseActivity;
import com.bytedance.tiktok.base.CommPagerAdapter;
import com.bytedance.tiktok.bean.MainPageChangeEvent;
import com.bytedance.tiktok.bean.PauseVideoEvent;
import com.bytedance.tiktok.fragment.MainFragment;
import com.bytedance.tiktok.fragment.PersonalHomeFragment;
import com.bytedance.tiktok.fragment.RecommendFragment;
import com.bytedance.tiktok.utils.RxBus;
import java.util.ArrayList;
import butterknife.BindView;
import rx.functions.Action1;

/**
 * create by libo
 * create on 2020/5/19
 * description 主页面
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private CommPagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public static int curMainPage;
    private MainFragment mainFragment = new MainFragment();
    private PersonalHomeFragment personalHomeFragment = new PersonalHomeFragment();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

        fragments.add(mainFragment);
        fragments.add(personalHomeFragment);
        pagerAdapter = new CommPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"",""});
        viewPager.setAdapter(pagerAdapter);

        //点击头像切换页面
        RxBus.getDefault().toObservable(MainPageChangeEvent.class)
                .subscribe((Action1<MainPageChangeEvent>) event -> {
                    viewPager.setCurrentItem(event.getPage());
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curMainPage = position;

                if (position == 0) {
                    RxBus.getDefault().post(new PauseVideoEvent(true));
                } else if (position == 1){
                    RxBus.getDefault().post(new PauseVideoEvent(false));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
