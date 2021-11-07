package com.bytedance.tiktok.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bytedance.tiktok.R
import com.bytedance.tiktok.adapter.PrivateLetterAdapter
import com.bytedance.tiktok.adapter.ShareAdapter
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.bean.ShareBean
import com.bytedance.tiktok.databinding.DialogShareBinding
import java.util.*

/**
 * create by libo
 * create on 2020-05-25
 * description 分享弹框
 */
class ShareDialog : BaseBottomSheetDialog() {

    private var _binding: DialogShareBinding? = null

    // This property is only valid between onCreateDialog and
    // onDestroyView.
    private val binding get() = _binding!!

    private val privateLetterAdapter: PrivateLetterAdapter = PrivateLetterAdapter()
    private val shareAdapter: ShareAdapter = ShareAdapter()
    private val shareBeans = ArrayList<ShareBean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init() {
        binding.rvPrivateLetter.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPrivateLetter.adapter = privateLetterAdapter
        privateLetterAdapter.submitList(DataCreate.userList)
        binding.rvShare.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvShare.adapter = shareAdapter
        shareAdapter.submitList(shareBeans)
        setShareDatas()
    }

    private fun setShareDatas() {
        shareBeans.add(ShareBean(R.string.icon_friends, "朋友圈", R.color.color_wechat_iconbg))
        shareBeans.add(ShareBean(R.string.icon_wechat, "微信", R.color.color_wechat_iconbg))
        shareBeans.add(ShareBean(R.string.icon_qq, "QQ", R.color.color_qq_iconbg))
        shareBeans.add(ShareBean(R.string.icon_qq_space, "QQ空间", R.color.color_qqzone_iconbg))
        shareBeans.add(ShareBean(R.string.icon_weibo, "微博", R.color.color_weibo_iconbg))
        shareBeans.add(ShareBean(R.string.icon_comment, "私信好友", R.color.color_FF0041))
        shareAdapter.submitList(shareBeans)
    }
}