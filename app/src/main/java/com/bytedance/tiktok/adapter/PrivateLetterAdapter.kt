package com.bytedance.tiktok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.PrivateLetterAdapter.PrivateLetterViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.databinding.ItemPrivateLetterBinding

/**
 * create by libo
 * create on 2020-05-25
 * description
 */
class PrivateLetterAdapter(context: Context?, datas: List<UserBean?>?) :
    BaseRvAdapter<UserBean?, PrivateLetterViewHolder?>(
        context!!, (datas as ArrayList<UserBean?>?)!!
    ) {
    protected override fun onBindData(
        holder: PrivateLetterViewHolder?,
        userBean: UserBean?,
        position: Int
    ) {
        holder?.binding?.ivHead!!.setImageResource(userBean!!.head)
        holder?.binding.tvNickname!!.text = userBean?.nickName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivateLetterViewHolder {
        return PrivateLetterViewHolder(ItemPrivateLetterBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class PrivateLetterViewHolder(val binding: ItemPrivateLetterBinding) : BaseRvViewHolder(binding.root) {

    }
}