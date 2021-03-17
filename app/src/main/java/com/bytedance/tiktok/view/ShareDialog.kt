package com.bytedance.tiktok.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.PrivateLetterAdapter
import com.bytedance.tiktok.adapter.ShareAdapter
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.bean.ShareBean
import java.util.*

/**
 * create by libo
 * create on 2020-05-25
 * description 分享弹框
 */
class ShareDialog : BaseBottomSheetDialog() {
    @JvmField
    @BindView(R.id.rv_private_letter)
    var rvPrivateLetter: RecyclerView? = null

    @JvmField
    @BindView(R.id.rv_share)
    var rvShare: RecyclerView? = null
    private var privateLetterAdapter: PrivateLetterAdapter? = null
    private var shareAdapter: ShareAdapter? = null
    private val shareBeans = ArrayList<ShareBean>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.dialog_share, container)
        ButterKnife.bind(this, view)
        init()
        return view
    }

    private fun init() {
        rvPrivateLetter!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        privateLetterAdapter = PrivateLetterAdapter(context, DataCreate.userList)
        rvPrivateLetter!!.adapter = privateLetterAdapter
        rvShare!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        shareAdapter = ShareAdapter(context, shareBeans)
        rvShare!!.adapter = shareAdapter
        setShareDatas()
    }

    private fun setShareDatas() {
        shareBeans.add(ShareBean(R.string.icon_friends, "朋友圈", R.color.color_wechat_iconbg))
        shareBeans.add(ShareBean(R.string.icon_wechat, "微信", R.color.color_wechat_iconbg))
        shareBeans.add(ShareBean(R.string.icon_qq, "QQ", R.color.color_qq_iconbg))
        shareBeans.add(ShareBean(R.string.icon_qq_space, "QQ空间", R.color.color_qqzone_iconbg))
        shareBeans.add(ShareBean(R.string.icon_weibo, "微博", R.color.color_weibo_iconbg))
        shareBeans.add(ShareBean(R.string.icon_comment, "私信好友", R.color.color_FF0041))
        shareAdapter!!.notifyDataSetChanged()
    }

    protected override val height: Int
        protected get() = dp2px(context!!, 355f)
}