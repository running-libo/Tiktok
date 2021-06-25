package com.bytedance.tiktok.activity

import androidx.fragment.app.Fragment
import com.bytedance.tiktok.R
import com.bytedance.tiktok.base.BaseActivity
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.fragment.FansFragment
import kotlinx.android.synthetic.main.activity_focus.*
import java.util.*

/**
 * create by libo
 * create on 2020-05-14
 * description 粉丝关注人页面
 */
class FocusActivity : BaseActivity() {

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private val titles = arrayOf("关注 128", "粉丝 128", "推荐关注")

    override fun setLayoutId(): Int {
        return R.layout.activity_focus
    }

    override fun init() {
        for (i in titles.indices) {
            fragments.add(FansFragment())
            tablayout!!.addTab(tablayout!!.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(supportFragmentManager, fragments, titles)
        viewpager!!.adapter = pagerAdapter
        tablayout!!.setupWithViewPager(viewpager)
    }
}