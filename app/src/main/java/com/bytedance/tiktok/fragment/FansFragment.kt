package com.bytedance.tiktok.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.FansAdapter
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.bean.DataCreate

class FansFragment : BaseFragment() {
    @BindView(R.id.recyclerview)
    var recyclerView: RecyclerView? = null
    private var fansAdapter: FansAdapter? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_fans
    }

    override fun init() {
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        fansAdapter = FansAdapter(context, DataCreate.userList)
        recyclerView!!.adapter = fansAdapter
    }
}