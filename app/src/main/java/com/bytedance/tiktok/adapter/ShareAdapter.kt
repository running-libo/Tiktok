package com.bytedance.tiktok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.ShareAdapter.ShareViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.ShareBean
import com.bytedance.tiktok.databinding.ItemShareBinding

/**
 * create by libo
 * create on 2020-05-25
 * description
 */
class ShareAdapter(context: Context?, datas: List<ShareBean?>?) :
    BaseRvAdapter<ShareBean?, ShareViewHolder?>(
        context!!, (datas as ArrayList<ShareBean?>?)!!
    ) {
    protected override fun onBindData(
        holder: ShareViewHolder?,
        shareBean: ShareBean?,
        position: Int
    ) {

        holder?.binding?.tvIcon!!.setText(shareBean!!.iconRes)
        holder?.binding?.tvText!!.text = shareBean?.text
        holder?.binding?.viewBg!!.setBackgroundResource(shareBean!!.bgRes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShareViewHolder {
        return ShareViewHolder(ItemShareBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class ShareViewHolder(val binding: ItemShareBinding) : BaseRvViewHolder(binding.root) {

    }
}