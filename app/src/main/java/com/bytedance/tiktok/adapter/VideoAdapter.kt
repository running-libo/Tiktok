package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemVideoBinding
import com.bytedance.tiktok.view.LikeView.OnLikeListener

class VideoAdapter : ListAdapter<VideoBean, VideoAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videoBean: VideoBean) {
            binding.controller.setVideoData(videoBean)
            binding.ivCover.setImageResource(videoBean.coverRes)
            binding.likeview.likeListener = object : OnLikeListener {
                override fun onLikeListener() {
                    if (!videoBean.isLiked) {  //未点赞，会有点赞效果，否则无
                        binding.controller.like()
                    }
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VideoBean>() {
        override fun areItemsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean {
            return oldItem.videoId == newItem.videoId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean {
            return oldItem == newItem
        }
    }
}