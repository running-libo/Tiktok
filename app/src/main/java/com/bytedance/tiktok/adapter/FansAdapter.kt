package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.databinding.ItemFansBinding

class FansAdapter : ListAdapter<UserBean, FansAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFansBinding.inflate(
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
        private val binding: ItemFansBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userBean: UserBean) {
            binding.ivHead.setImageResource(userBean.head)
            binding.tvNickname.text = userBean.nickName
            binding.tvFocus.text = if (userBean.isFocused) "已关注" else "关注"
            binding.tvFocus.setOnClickListener { v: View? ->
                if (!userBean.isFocused) {
                    binding.tvFocus.text = "已关注"
                    binding.tvFocus.setBackgroundResource(R.drawable.shape_round_halfwhite)
                } else {
                    binding.tvFocus.text = "关注"
                    binding.tvFocus.setBackgroundResource(R.drawable.shape_round_red)
                }
                userBean.isFocused = !userBean.isFocused
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<UserBean>() {
        override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
            return oldItem.uid == newItem.uid
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
            return oldItem == newItem
        }
    }
}