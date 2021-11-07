package com.bytedance.tiktok.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.databinding.ActivityMainBinding
import com.bytedance.tiktok.fragment.MainFragment
import com.bytedance.tiktok.fragment.PersonalHomeFragment
import com.bytedance.tiktok.utils.RxBus
import rx.Subscription
import rx.functions.Action1
import java.util.*

/**
 * 主頁面
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var pagerAdapter: CommPagerAdapter? = null
    private val fragments = ArrayList<Fragment>()

    /** 上次点击返回键时间  */
    private var lastTime: Long = 0

    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
    }

    private fun init() {
        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        fragments.add(MainFragment.newInstance())
        fragments.add(PersonalHomeFragment.newInstance())
        pagerAdapter = CommPagerAdapter(supportFragmentManager, fragments, arrayOf("", ""))
        binding.viewPager.adapter = pagerAdapter

        //点击头像切换页面
        subscription = RxBus.getDefault().toObservable(MainPageChangeEvent::class.java)
                .subscribe(Action1 { event: MainPageChangeEvent ->
                    binding.viewPager.currentItem = event.page
                } as Action1<MainPageChangeEvent>)
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
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
            if (binding.viewPager.currentItem == 1) {
                binding.viewPager.currentItem = 0
            } else {
                Toast.makeText(applicationContext, "再按一次退出", Toast.LENGTH_SHORT).show()
                lastTime = System.currentTimeMillis()
            }
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        /** 连续按返回键退出时间  */
        private const val EXIT_TIME = 2000

        var curMainPage = 0
    }
}