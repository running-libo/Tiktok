package com.bytedance.tiktok.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.databinding.FragmentMainBinding
import com.bytedance.tiktok.utils.RxBus
import java.util.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragments()
        setMainMenu()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFragments() {
        fragments.add(CurrentLocationFragment.newInstance())
        fragments.add(RecommendFragment.newInstance())
        binding.tabTitle.addTab(binding.tabTitle.newTab().setText("海淀"))
        binding.tabTitle.addTab(binding.tabTitle.newTab().setText("推荐"))
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, arrayOf("海淀", "推荐"))
        binding.viewPager.adapter = pagerAdapter
        binding.tabTitle.setupWithViewPager(binding.viewPager)
        binding.tabTitle.getTabAt(1)?.select()
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

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
        binding.tabMainMenu.addTab(binding.tabMainMenu.newTab().setText("首页"))
        binding.tabMainMenu.addTab(binding.tabMainMenu.newTab().setText("好友"))
        binding.tabMainMenu.addTab(binding.tabMainMenu.newTab().setText(""))
        binding.tabMainMenu.addTab(binding.tabMainMenu.newTab().setText("消息"))
        binding.tabMainMenu.addTab(binding.tabMainMenu.newTab().setText("我"))
    }

    companion object {
        /** 默认显示第一页推荐页  */
        var curPage = 1

        fun newInstance() = MainFragment()
    }
}