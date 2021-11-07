package com.bytedance.tiktok.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.FocusActivity
import com.bytedance.tiktok.activity.ShowImageActivity
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.CurUserBean
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.databinding.FragmentPersonalHomeBinding
import com.bytedance.tiktok.utils.NumUtils
import com.bytedance.tiktok.utils.RxBus
import com.google.android.material.appbar.AppBarLayout
import rx.Subscription
import rx.functions.Action1
import java.util.*
import kotlin.math.abs

/**
 * 个人主页fragment
 */
class PersonalHomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPersonalHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private var curUserBean: UserBean? = null
    private var subscription: Subscription? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //解决toolbar左边距问题
        binding.toolbar.setContentInsetsAbsolute(0, 0)
        setAppbarLayoutPercent()
        binding.ivReturn.setOnClickListener(this)
        binding.personalHomeHeader.ivHead.setOnClickListener(this)
        binding.ivBg.setOnClickListener(this)
        binding.personalHomeHeader.llFocus.setOnClickListener(this)
        binding.personalHomeHeader.llFans.setOnClickListener(this)
        setUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription?.unsubscribe()
        _binding = null
    }

    /**
     * 过渡动画跳转页面
     */
    private fun transitionAnim(view: View, res: Int) {
        val activity = activity ?: return
        val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity,
            view,
            getString(R.string.trans)
        )
        val intent = Intent(activity, ShowImageActivity::class.java)
        intent.putExtra("res", res)
        ActivityCompat.startActivity(activity, intent, compat.toBundle())
    }

    private fun setUserInfo() {
        subscription = RxBus.getDefault().toObservable(CurUserBean::class.java)
            .subscribe(Action1 { curUserBean: CurUserBean ->
                coordinatorLayoutBackTop()
                this.curUserBean = curUserBean.userBean
                binding.ivBg.setImageResource(curUserBean.userBean.head)
                binding.personalHomeHeader.ivHead.setImageResource(curUserBean.userBean.head)
                binding.personalHomeHeader.tvNickname.text = curUserBean.userBean.nickName
                binding.personalHomeHeader.tvSign.text = curUserBean.userBean.sign
                binding.tvTitle.text = curUserBean.userBean.nickName
                val subCount = NumUtils.numberFilter(curUserBean.userBean.subCount)
                val focusCount = NumUtils.numberFilter(curUserBean.userBean.focusCount)
                val fansCount = NumUtils.numberFilter(curUserBean.userBean.fansCount)

                //获赞 关注 粉丝
                binding.personalHomeHeader.tvGetLikeCount.text = subCount
                binding.personalHomeHeader.tvFocusCount.text = focusCount
                binding.personalHomeHeader.tvFansCount.text = fansCount

                //关注状态
                if (curUserBean.userBean.isFocused) {
                    binding.personalHomeHeader.tvAddfocus.text = "已关注"
                    binding.personalHomeHeader.tvAddfocus.setBackgroundResource(R.drawable.shape_round_halfwhite)
                } else {
                    binding.personalHomeHeader.tvAddfocus.text = "关注"
                    binding.personalHomeHeader.tvAddfocus.setBackgroundResource(R.drawable.shape_round_red)
                }
                setTabLayout()
            } as Action1<CurUserBean>)
    }

    private fun setTabLayout() {
        val titles = arrayOf(
            "作品 " + curUserBean?.workCount,
            "动态 " + curUserBean?.dynamicCount,
            "喜欢 " + curUserBean?.likeCount
        )
        fragments.clear()
        for (i in titles.indices) {
            fragments.add(WorkFragment.newInstance())
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, titles)
        binding.viewPager.adapter = pagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }

    /**
     * 根据滚动比例渐变view
     */
    private fun setAppbarLayoutPercent() {
        binding.appbarlayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appbarLayout, verticalOffset ->
            val percent = abs(verticalOffset * 1.0f) / appbarLayout.totalScrollRange //滑动比例
            if (percent > 0.8) {
                binding.tvTitle.visibility = View.VISIBLE
                binding.tvFocus.visibility = View.VISIBLE
                val alpha = 1 - (1 - percent) * 5 //渐变变换
                binding.tvTitle.alpha = alpha
                binding.tvFocus.alpha = alpha
            } else {
                binding.tvTitle.visibility = View.GONE
                binding.tvFocus.visibility = View.GONE
            }
        })
    }

    /**
     * 自动回顶部
     */
    private fun coordinatorLayoutBackTop() {
        val behavior =
            (binding.appbarlayout.layoutParams as CoordinatorLayout.LayoutParams).behavior
        if (behavior is AppBarLayout.Behavior) {
            val topAndBottomOffset = behavior.topAndBottomOffset
            if (topAndBottomOffset != 0) {
                behavior.topAndBottomOffset = 0
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivReturn -> RxBus.getDefault().post(MainPageChangeEvent(0))
            R.id.ivHead -> {
                curUserBean?.head?.let {
                    transitionAnim(binding.personalHomeHeader.ivHead, it)
                }
            }
            R.id.ivBg -> {
            }
            R.id.llFocus -> startActivity(Intent(activity, FocusActivity::class.java))
            R.id.llFans -> startActivity(Intent(activity, FocusActivity::class.java))
        }
    }

    companion object {
        fun newInstance() = PersonalHomeFragment()
    }
}