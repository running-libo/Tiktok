package com.bytedance.tiktok.activity

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseActivity
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.fragment.MainFragment
import com.bytedance.tiktok.fragment.PersonalHomeFragment
import com.bytedance.tiktok.utils.RxBus
import kotlinx.android.synthetic.main.activity_main.*
import rx.functions.Action1
import java.util.*

/**
 * create by libo
 * create on 2020/5/19
 * description 主页面
 */
class MainActivity : BaseActivity() {
    private var pagerAdapter: CommPagerAdapter? = null
    private val fragments = ArrayList<Fragment>()
    private val mainFragment = MainFragment()
    private val personalHomeFragment = PersonalHomeFragment()

    /** 上次点击返回键时间  */
    private var lastTime: Long = 0

    /** 连续按返回键退出时间  */
    private val EXIT_TIME = 2000
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun init() {

        try {
            Thread.sleep(2000)
        } catch(e: Exception) {
            e.printStackTrace()
        }


        fragments.add(mainFragment)
        fragments.add(personalHomeFragment)
        pagerAdapter = CommPagerAdapter(supportFragmentManager, fragments, arrayOf("", ""))
        viewPager!!.adapter = pagerAdapter

        //点击头像切换页面
        RxBus.getDefault().toObservable(MainPageChangeEvent::class.java)
                .subscribe(Action1 { event: MainPageChangeEvent ->
                    if (viewPager != null) {
                        viewPager!!.currentItem = event.page
                    }
                } as Action1<MainPageChangeEvent>)
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                curMainPage = position
                if (position == 0) {
                    RxBus.getDefault().post(PauseVideoEvent(true))
                } else if (position == 1) {
                    RxBus.getDefault().post(PauseVideoEvent(false))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onBackPressed() {
        //双击返回退出App
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            if (viewPager!!.currentItem == 1) {
                viewPager!!.currentItem = 0
            } else {
                Toast.makeText(applicationContext, "再按一次退出", Toast.LENGTH_SHORT).show()
                lastTime = System.currentTimeMillis()
            }
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        var curMainPage = 0
    }
}