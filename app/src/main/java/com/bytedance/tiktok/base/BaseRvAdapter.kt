package com.bytedance.tiktok.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * create by libo
 * create on 2018/11/15
 * description RecyclerAdapter基类
 * tip多套布局T传Object类型，其他直接传具体类型
 */
abstract class BaseRvAdapter<T, VH : BaseRvViewHolder?>(val context: Context, protected var mDatas: MutableList<T>) : RecyclerView.Adapter<VH>() {

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindData(holder, mDatas!![position], position)
    }

    override fun getItemCount(): Int {
        return if (mDatas == null) 0 else mDatas.size
    }

    protected abstract fun onBindData(holder: VH, data: T, position: Int)

    fun addData(data: T) {
        mDatas!!.add(data)
        notifyDataSetChanged()
    }

    fun addDataToPostion(data: T, position: Int) {
        mDatas!!.add(position, data)
        notifyItemInserted(position)
    }

    fun addDatas(datas: List<T>) {
        val oldCount = mDatas!!.size
        mDatas!!.addAll(datas)
        notifyItemRangeInserted(oldCount, datas.size)
    }

    fun removeDataFromPosition(position: Int) {
        mDatas!!.removeAt(position)
        notifyDataSetChanged()
    }

    fun onlyRemoveItem(position: Int) {
        mDatas!!.removeAt(position)
    }

    val datas: List<T>?
        get() = mDatas

    fun clearDatas() {
        mDatas!!.clear()
        notifyDataSetChanged()
    }
}