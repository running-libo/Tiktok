package com.bytedance.tiktok.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife

/**
 * create by libo
 * create on 2018/11/15
 * description Recycler ViewHolder基类
 */
open class BaseRvViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    init {
        ButterKnife.bind(this, itemView!!)
    }
}