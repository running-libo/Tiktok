package com.bytedance.tiktok.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.adapter.WorkAdapter.WorkViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemWorkBinding
import com.bytedance.tiktok.utils.NumUtils.numberFilter

/**
 * create by libo
 * create on 2020-05-21
 * description
 */
class WorkAdapter(context: Context?, datas: List<VideoBean?>?) :
    BaseRvAdapter<VideoBean?, WorkViewHolder?>(
        context!!, (datas as ArrayList<VideoBean?>?)!!
    ) {
    protected override fun onBindData(holder: WorkViewHolder?, videoBean: VideoBean?, position: Int) {
        holder?.binding?.ivCover!!.setImageResource(videoBean!!.coverRes)
        holder?.binding?.tvLikecount!!.text = numberFilter(videoBean.likeCount)
        holder?.binding.root.setOnClickListener { v: View? ->
            PlayListActivity.initPos = position
            context.startActivity(Intent(context, PlayListActivity::class.java))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkViewHolder {
        return WorkViewHolder(ItemWorkBinding.inflate(LayoutInflater.from(context)))
    }

    inner class WorkViewHolder(val binding: ItemWorkBinding) : BaseRvViewHolder(binding.root) {

    }
}