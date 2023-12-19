package com.bytedance.tiktok.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.adapter.GridVideoAdapter.GridVideoViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemGridvideoBinding
import com.bytedance.tiktok.view.IconFontTextView

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
class GridVideoAdapter(context: Context?, datas: List<VideoBean?>?) :
    BaseRvAdapter<VideoBean?, GridVideoViewHolder?>(
        context!!, (datas as ArrayList<VideoBean?>?)!!
    ) {
    override fun onBindData(
        holder: GridVideoViewHolder?,
        videoBean: VideoBean?,
        position: Int
    ) {
        videoBean?.let {

            holder?.binding?.ivCover!!.setBackgroundResource(it.coverRes)
            holder?.binding?.tvContent?.text = it.content
            holder?.binding?.tvDistance!!.text = it.distance.toString() + " km"
            holder?.binding?.ivHead!!.setImageResource(it.userBean!!.head)
            holder?.binding?.root?.setOnClickListener { v: View? ->
                PlayListActivity.initPos = position
                context.startActivity(Intent(context, PlayListActivity::class.java))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridVideoViewHolder {
        return GridVideoViewHolder(ItemGridvideoBinding.inflate(LayoutInflater.from(context)))
    }

    inner class GridVideoViewHolder(val binding: ItemGridvideoBinding) : BaseRvViewHolder(binding.root) {

    }
}