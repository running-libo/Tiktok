package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.adapter.GridVideoAdapter.GridVideoViewHolder
import com.bytedance.tiktok.base.BaseAdapter
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemGridvideoBinding

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
class GridVideoAdapter(val context: Context) : BaseAdapter<GridVideoViewHolder, VideoBean>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridVideoViewHolder {
        return GridVideoViewHolder(ItemGridvideoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GridVideoViewHolder, @SuppressLint("RecyclerView") position: Int) {
        mList[holder.adapterPosition]?.let {

            Glide.with(context)
                .asBitmap()
                .load(it.videoRes)
                .apply(RequestOptions.frameOf(0))  // 从第一帧开始
                .into(holder.binding.ivCover)
            holder?.binding?.tvContent?.text = it.content
            holder?.binding?.tvDistance!!.text = it.distance.toString() + " km"
            holder?.binding?.ivHead!!.setImageResource(it.userBean!!.head)
            holder?.binding?.root?.setOnClickListener { v: View? ->
                PlayListActivity.initPos = position
                context.startActivity(Intent(context, PlayListActivity::class.java))
            }
        }
    }

    inner class GridVideoViewHolder(val binding: ItemGridvideoBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}