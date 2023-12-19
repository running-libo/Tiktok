package com.bytedance.tiktok.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.GridVideoAdapter
import com.bytedance.tiktok.base.BaseBindingFragment
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.databinding.FragmentCurrentLocationBinding

/**
 * create by libo
 * create on 2020-05-19
 * description 附近的人fragment
 */
class CurrentLocationFragment : BaseBindingFragment<FragmentCurrentLocationBinding>({FragmentCurrentLocationBinding.inflate(it)}) {
    private var adapter: GridVideoAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
        binding.recyclerView!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = GridVideoAdapter(requireContext())
        adapter?.appendList(DataCreate.datas)
        binding.recyclerView!!.adapter = adapter
        binding.refreshLayout!!.setColorSchemeResources(R.color.color_link)
        binding.refreshLayout!!.setOnRefreshListener {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    binding.refreshLayout!!.isRefreshing = false
                }
            }.start()
        }
    }
}