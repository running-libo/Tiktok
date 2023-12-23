package com.bytedance.tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.adapter.PrivateLetterAdapter.PrivateLetterViewHolder
import com.bytedance.tiktok.base.BaseAdapter
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.databinding.ItemPrivateLetterBinding

/**
 * create by libo
 * create on 2020-05-25
 * description
 */
class PrivateLetterAdapter : BaseAdapter<PrivateLetterViewHolder, UserBean>(FansDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivateLetterViewHolder {
        return PrivateLetterViewHolder(ItemPrivateLetterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PrivateLetterViewHolder, position: Int) {
        var userBean = mList[position]
        holder?.binding?.ivHead!!.setImageResource(userBean!!.head)
        holder?.binding.tvNickname!!.text = userBean?.nickName
    }

    inner class PrivateLetterViewHolder(val binding: ItemPrivateLetterBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}