package com.bytedance.tiktok.activity

import androidx.fragment.app.Fragment
import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseBindingActivity
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.databinding.ActivityFocusBinding
import com.bytedance.tiktok.fragment.FansFragment
import java.util.*

/**
 * create by libo
 * create on 2020-05-14
 * description 粉丝关注人页面
 */
class FocusActivity : BaseBindingActivity<ActivityFocusBinding>({ActivityFocusBinding.inflate(it)}) {

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private val titles = arrayOf("关注 128", "粉丝 128", "推荐关注")

    override fun init() {
        for (i in titles.indices) {
            fragments.add(FansFragment())
            binding.tablayout.addTab(binding.tablayout.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(supportFragmentManager, fragments, titles)
        binding.viewpager.adapter = pagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)
    }
}