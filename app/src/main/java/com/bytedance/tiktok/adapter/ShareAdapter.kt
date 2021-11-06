package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.bean.ShareBean
import com.bytedance.tiktok.databinding.ItemShareBinding

class ShareAdapter : ListAdapter<ShareBean, ShareAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemShareBinding.inflate(
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
        private val binding: ItemShareBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shareBean: ShareBean) {
            binding.tvIcon.setText(shareBean.iconRes)
            binding.tvText.text = shareBean.text
            binding.viewBg.setBackgroundResource(shareBean.bgRes)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ShareBean>() {
        override fun areItemsTheSame(oldItem: ShareBean, newItem: ShareBean): Boolean {
            return oldItem.text == newItem.text
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ShareBean, newItem: ShareBean): Boolean {
            return oldItem == newItem
        }

    }
}