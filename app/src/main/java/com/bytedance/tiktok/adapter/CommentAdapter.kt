package com.bytedance.tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.adapter.CommentAdapter.CommentViewHolder
import com.bytedance.tiktok.base.BaseAdapter
import com.bytedance.tiktok.bean.CommentBean
import com.bytedance.tiktok.databinding.ItemCommentBinding
import com.bytedance.tiktok.utils.NumUtils.numberFilter

/**
 * create by libo
 * create on 2020-05-24
 * description
 */
class CommentAdapter : BaseAdapter<CommentViewHolder, CommentBean>(CommentDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder?.binding?.let {
            var commentBean = mList[position]
            it.ivHead!!.setImageResource(commentBean?.userBean!!.head)
            it.tvNickname!!.text = commentBean?.userBean!!.nickName
            it.tvContent!!.text = commentBean?.content
            it.tvLikecount!!.text = numberFilter(commentBean.likeCount)
        }
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}

class CommentDiff: DiffUtil.ItemCallback<CommentBean>() {
    override fun areItemsTheSame(oldItem: CommentBean, newItem: CommentBean): Boolean {
        return (oldItem.content == newItem.content && oldItem.userBean!!.uid == newItem.userBean!!.uid)
    }

    override fun areContentsTheSame(oldItem: CommentBean, newItem: CommentBean): Boolean {
        return (oldItem.content == newItem.content && oldItem.userBean!!.uid == newItem.userBean!!.uid)
    }

}