package com.bytedance.tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.adapter.VideoAdapter.VideoViewHolder
import com.bytedance.tiktok.base.BaseAdapter
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemVideoBinding
import com.bytedance.tiktok.view.LikeView.OnLikeListener

/**
 * create by libo
 * create on 2020-05-20
 * description
 */
class VideoAdapter: BaseAdapter<VideoViewHolder, VideoBean>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        mList[position]?.let {
            holder.binding.controller.setVideoData(it)
            holder?.binding?.ivCover?.setImageResource(it.coverRes)
            holder?.binding?.likeview?.setOnLikeListener(object: OnLikeListener {
                override fun onLikeListener() {
                    if (!it.isLiked) {  //未点赞，会有点赞效果，否则无
                        holder?.binding?.controller!!.like()
                    }

                }
            })
        }
    }

    inner class VideoViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}