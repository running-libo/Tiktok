package com.bytedance.tiktok.fragment

import android.os.CountDownTimer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.GridVideoAdapter
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.bean.DataCreate

/**
 * create by libo
 * create on 2020-05-19
 * description 附近的人fragment
 */
class CurrentLocationFragment : BaseFragment() {
    @BindView(R.id.recyclerview)
    var recyclerView: RecyclerView? = null
    private var adapter: GridVideoAdapter? = null

    @BindView(R.id.refreshlayout)
    var refreshLayout: SwipeRefreshLayout? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_current_location
    }

    override fun init() {
        DataCreate().initData()
        recyclerView!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = GridVideoAdapter(activity, DataCreate.datas)
        recyclerView!!.adapter = adapter
        refreshLayout!!.setColorSchemeResources(R.color.color_link)
        refreshLayout!!.setOnRefreshListener {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    refreshLayout!!.isRefreshing = false
                }
            }.start()
        }
    }
}