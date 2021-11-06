package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemGridvideoBinding

class GridVideoAdapter : ListAdapter<VideoBean, GridVideoAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGridvideoBinding.inflate(
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
        private val binding: ItemGridvideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videoBean: VideoBean) {
            binding.ivCover.setBackgroundResource(videoBean.coverRes)
            binding.tvContent.text = videoBean.content
            binding.tvDistance.text = "${videoBean.distance} km"
            videoBean.userBean?.head?.let {
                binding.ivHead.setImageResource(it)
            }
            binding.root.setOnClickListener {
                val context = binding.root.context ?: return@setOnClickListener
                PlayListActivity.initPos = adapterPosition
                context.startActivity(Intent(context, PlayListActivity::class.java))
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