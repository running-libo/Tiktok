package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.bean.VideoBean
import com.bytedance.tiktok.databinding.ItemPrivateLetterBinding

class PrivateLetterAdapter : ListAdapter<VideoBean.UserBean, PrivateLetterAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPrivateLetterBinding.inflate(
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
        private val binding: ItemPrivateLetterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userBean: VideoBean.UserBean) {
            binding.ivHead.setImageResource(userBean.head)
            binding.tvNickname.text = userBean.nickName
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<VideoBean.UserBean>() {

        override fun areItemsTheSame(
            oldItem: VideoBean.UserBean,
            newItem: VideoBean.UserBean
        ): Boolean {
            return oldItem.uid == newItem.uid
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: VideoBean.UserBean,
            newItem: VideoBean.UserBean
        ): Boolean {
            return oldItem == newItem
        }
    }
}