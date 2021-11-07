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
import com.bytedance.tiktok.databinding.ItemWorkBinding
import com.bytedance.tiktok.utils.NumUtils.numberFilter

class WorkAdapter : ListAdapter<VideoBean, WorkAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWorkBinding.inflate(
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
        private val binding: ItemWorkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(videoBean: VideoBean) {
            binding.ivCover.setImageResource(videoBean.coverRes)
            binding.tvLikecount.text = numberFilter(videoBean.likeCount)
            binding.root.setOnClickListener {
                val context = binding.root.context ?: return@setOnClickListener
                PlayListActivity.initPos = adapterPosition
                context.startActivity(Intent(context, PlayListActivity::class.java))
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<VideoBean>() {
        override fun areItemsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean {
            return oldItem.videoId == newItem.videoId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VideoBean, newItem: VideoBean): Boolean {
            return oldItem == newItem
        }

    }
}