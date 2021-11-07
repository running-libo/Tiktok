package com.bytedance.tiktok.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.databinding.ActivityFocusBinding
import com.bytedance.tiktok.fragment.FansFragment
import java.util.*

/**
 * 粉丝关注人页面
 */
class FocusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFocusBinding

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private val titles = arrayOf("关注 128", "粉丝 128", "推荐关注")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFocusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        for (i in titles.indices) {
            fragments.add(FansFragment.newInstance())
            binding.tablayout.addTab(binding.tablayout.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(supportFragmentManager, fragments, titles)
        binding.viewpager.adapter = pagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)
    }
}