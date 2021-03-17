package com.bytedance.tiktok.fragment

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.utils.RxBus
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*

/**
 * create by libo
 * create on 2020-05-19
 * description 主页fragment
 */
class MainFragment : BaseFragment() {
    private var currentLocationFragment: CurrentLocationFragment? = null
    private var recommendFragment: RecommendFragment? = null

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun init() {
        setFragments()
        setMainMenu()
    }

    private fun setFragments() {
        currentLocationFragment = CurrentLocationFragment()
        recommendFragment = RecommendFragment()
        fragments.add(currentLocationFragment!!)
        fragments.add(recommendFragment!!)
        tabTitle!!.addTab(tabTitle!!.newTab().setText("海淀"))
        tabTitle!!.addTab(tabTitle!!.newTab().setText("推荐"))
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, arrayOf("海淀", "推荐"))
        viewPager!!.adapter = pagerAdapter
        tabTitle!!.setupWithViewPager(viewPager)
        tabTitle!!.getTabAt(1)!!.select()
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                curPage = position
                if (position == 1) {
                    //继续播放
                    RxBus.getDefault().post(PauseVideoEvent(true))
                } else {
                    //切换到其他页面，需要暂停视频
                    RxBus.getDefault().post(PauseVideoEvent(false))
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun setMainMenu() {
        tabMainMenu!!.addTab(tabMainMenu!!.newTab().setText("首页"))
        tabMainMenu!!.addTab(tabMainMenu!!.newTab().setText("好友"))
        tabMainMenu!!.addTab(tabMainMenu!!.newTab().setText(""))
        tabMainMenu!!.addTab(tabMainMenu!!.newTab().setText("消息"))
        tabMainMenu!!.addTab(tabMainMenu!!.newTab().setText("我"))
    }

    companion object {
        /** 默认显示第一页推荐页  */
        var curPage = 1
    }
}