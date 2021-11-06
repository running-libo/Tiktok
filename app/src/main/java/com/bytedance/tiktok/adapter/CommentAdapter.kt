package com.bytedance.tiktok.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.bean.CommentBean
import com.bytedance.tiktok.databinding.ItemCommentBinding
import com.bytedance.tiktok.utils.NumUtils.numberFilter

class CommentAdapter : ListAdapter<CommentBean, CommentAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCommentBinding.inflate(
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
        private val binding: ItemCommentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(commentBean: CommentBean) {
            commentBean.userBean?.head?.let {
                binding.ivHead.setImageResource(it)
            }
            binding.tvNickname.text = commentBean.userBean?.nickName
            binding.tvContent.text = commentBean.content
            binding.tvLikecount.text = numberFilter(commentBean.likeCount)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CommentBean>() {
        override fun areItemsTheSame(oldItem: CommentBean, newItem: CommentBean): Boolean {
            return oldItem.userBean?.uid == newItem.userBean?.uid
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CommentBean, newItem: CommentBean): Boolean {
            return oldItem == newItem
        }
    }
}