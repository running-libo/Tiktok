package com.bytedance.tiktok.base

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * create by libo
 * create on 2020/5/19
 * description 公共viewPageradapter
 */
class CommPagerAdapter(fm: FragmentManager?, private val items: ArrayList<out Fragment>, private val mTitles: Array<String>) : FragmentStatePagerAdapter(fm!!) {
    override fun getCount(): Int {
        return if (items.size == 0) 0 else items.size
    }

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun saveState(): Parcelable? {
        return null
    }
}