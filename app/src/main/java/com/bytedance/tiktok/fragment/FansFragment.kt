package com.bytedance.tiktok.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.FansAdapter
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.bean.DataCreate
import kotlinx.android.synthetic.main.fragment_fans.*

class FansFragment : BaseFragment() {
    private var fansAdapter: FansAdapter? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_fans
    }

    override fun init() {
        recyclerview!!.layoutManager = LinearLayoutManager(context)
        fansAdapter = FansAdapter(context, DataCreate.userList)
        recyclerview!!.adapter = fansAdapter
    }
}