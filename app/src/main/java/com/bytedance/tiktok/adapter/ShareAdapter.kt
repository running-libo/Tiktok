package com.bytedance.tiktok.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.tiktok.adapter.ShareAdapter.ShareViewHolder
import com.bytedance.tiktok.base.BaseAdapter
import com.bytedance.tiktok.bean.ShareBean
import com.bytedance.tiktok.databinding.ItemShareBinding

/**
 * create by libo
 * create on 2020-05-25
 * description
 */
class ShareAdapter : BaseAdapter<ShareViewHolder, ShareBean>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareViewHolder {
        return ShareViewHolder(ItemShareBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ShareViewHolder, position: Int) {
        var shareBean = mList[position]
        holder?.binding?.tvIcon!!.setText(shareBean!!.iconRes)
        holder?.binding?.tvText!!.text = shareBean?.text
        holder?.binding?.viewBg!!.setBackgroundResource(shareBean!!.bgRes)
    }

    inner class ShareViewHolder(val binding: ItemShareBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}