package com.bytedance.tiktok.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.adapter.FansAdapter
import com.bytedance.tiktok.base.BaseBindingFragment
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.databinding.FragmentFansBinding

class FansFragment : BaseBindingFragment<FragmentFansBinding>({FragmentFansBinding.inflate(it)}) {
    private var fansAdapter: FansAdapter? = null

    companion object {
        val recyclerViewPool = RecyclerView.RecycledViewPool()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }
    fun init() {
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.setRecycledViewPool(recyclerViewPool)
        fansAdapter = FansAdapter()
        fansAdapter?.appendList(DataCreate.userList)
        binding.recyclerview.adapter = fansAdapter
    }
}