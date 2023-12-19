package com.bytedance.tiktok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.CommentAdapter.CommentViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.CommentBean
import com.bytedance.tiktok.databinding.ItemCommentBinding
import com.bytedance.tiktok.utils.NumUtils.numberFilter
import com.bytedance.tiktok.view.CircleImageView

/**
 * create by libo
 * create on 2020-05-24
 * description
 */
class CommentAdapter(context: Context?, datas: List<CommentBean?>?) :
    BaseRvAdapter<CommentBean?, CommentViewHolder?>(
        context!!, (datas as ArrayList<CommentBean?>?)!!
    ) {
    protected override fun onBindData(
        holder: CommentViewHolder?,
        commentBean: CommentBean?,
        position: Int
    ) {
        holder?.binding?.let {
            it.ivHead!!.setImageResource(commentBean?.userBean!!.head)
            it.tvNickname!!.text = commentBean?.userBean!!.nickName
            it.tvContent!!.text = commentBean?.content
            it.tvLikecount!!.text = numberFilter(commentBean.likeCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding) : BaseRvViewHolder(binding.root) {

    }
}