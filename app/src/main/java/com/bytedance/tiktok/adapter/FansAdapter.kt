package com.bytedance.tiktok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.FansAdapter.FansViewHolder
import com.bytedance.tiktok.base.BaseRvAdapter
import com.bytedance.tiktok.base.BaseRvViewHolder
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.databinding.ItemFansBinding
import com.bytedance.tiktok.view.CircleImageView

/**
 * create by libo
 * create on 2020-05-24
 * description
 */
class FansAdapter(context: Context?, datas: List<UserBean?>?) :
    BaseRvAdapter<UserBean?, FansViewHolder?>(
        context!!, (datas as ArrayList<UserBean?>?)!!
    ) {
    protected override fun onBindData(holder: FansViewHolder?, userBean: UserBean?, position: Int) {
        holder?.binding?.let {
            it.ivHead!!.setImageResource(userBean!!.head)
            it.tvNickname!!.text = userBean.nickName
            it.tvFocus!!.text = if (userBean.isFocused) "已关注" else "关注"
            it.tvFocus!!.setOnClickListener { v: View? ->
                if (!userBean.isFocused) {
                    it.tvFocus!!.text = "已关注"
                    it.tvFocus!!.setBackgroundResource(R.drawable.shape_round_halfwhite)
                } else {
                    it.tvFocus!!.text = "关注"
                    it.tvFocus!!.setBackgroundResource(R.drawable.shape_round_red)
                }
                userBean.isFocused = !userBean.isFocused
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FansViewHolder {
        return FansViewHolder(ItemFansBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    inner class FansViewHolder(val binding: ItemFansBinding) : BaseRvViewHolder(binding.root) {

    }
}