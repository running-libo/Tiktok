package com.bytedance.tiktok.fragment

import android.content.Intent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.FocusActivity
import com.bytedance.tiktok.activity.ShowImageActivity
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.base.CommPagerAdapter
import com.bytedance.tiktok.bean.CurUserBean
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.VideoBean.UserBean
import com.bytedance.tiktok.utils.NumUtils
import com.bytedance.tiktok.utils.RxBus
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.fragment_personal_home.*
import kotlinx.android.synthetic.main.personal_home_header.*
import rx.Subscription
import rx.functions.Action1
import java.util.*

/**
 * create by libo
 * create on 2020-05-19
 * description 个人主页fragment
 */
class PersonalHomeFragment : BaseFragment(), View.OnClickListener {
    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private var curUserBean: UserBean? = null
    private var subscription: Subscription? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_personal_home
    }

    override fun init() {

        //解决toolbar左边距问题
        toolbar!!.setContentInsetsAbsolute(0, 0)
        setappbarlayoutPercent()
        ivReturn!!.setOnClickListener(this)
        ivHead!!.setOnClickListener(this)
        ivBg!!.setOnClickListener(this)
        llFocus!!.setOnClickListener(this)
        llFans!!.setOnClickListener(this)
        setUserInfo()
    }

    /**
     * 过渡动画跳转页面
     *
     * @param view
     */
    fun transitionAnim(view: View?, res: Int) {
        val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, view!!, getString(R.string.trans))
        val intent = Intent(activity, ShowImageActivity::class.java)
        intent.putExtra("res", res)
        ActivityCompat.startActivity(activity!!, intent, compat.toBundle())
    }

    fun setUserInfo() {
        subscription = RxBus.getDefault().toObservable(CurUserBean::class.java).subscribe(Action1 { curUserBean: CurUserBean ->
            coordinatorLayoutBackTop()
            this.curUserBean = curUserBean.userBean
            ivBg!!.setImageResource(curUserBean.userBean.head)
            ivHead!!.setImageResource(curUserBean.userBean.head)
            tvNickname!!.text = curUserBean.userBean.nickName
            tvSign!!.text = curUserBean.userBean.sign
            tvTitle!!.text = curUserBean.userBean.nickName
            val subCount = NumUtils.numberFilter(curUserBean.userBean.subCount)
            val focusCount = NumUtils.numberFilter(curUserBean.userBean.focusCount)
            val fansCount = NumUtils.numberFilter(curUserBean.userBean.fansCount)

            //获赞 关注 粉丝
            tvGetLikeCount!!.text = subCount
            tvFocusCount!!.text = focusCount
            tvFansCount!!.text = fansCount

            //关注状态
            if (curUserBean.userBean.isFocused) {
                tvAddfocus!!.text = "已关注"
                tvAddfocus!!.setBackgroundResource(R.drawable.shape_round_halfwhite)
            } else {
                tvAddfocus!!.text = "关注"
                tvAddfocus!!.setBackgroundResource(R.drawable.shape_round_red)
            }
            setTabLayout()
        } as Action1<CurUserBean>)
    }

    private fun setTabLayout() {
        val titles = arrayOf("作品 " + curUserBean!!.workCount, "动态 " + curUserBean!!.dynamicCount, "喜欢 " + curUserBean!!.likeCount)
        fragments.clear()
        for (i in titles.indices) {
            fragments.add(WorkFragment())
            tabLayout!!.addTab(tabLayout!!.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, titles)
        viewPager!!.adapter = pagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
    }

    /**
     * 根据滚动比例渐变view
     */
    private fun setappbarlayoutPercent() {
        appbarlayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appbarlayout, verticalOffset ->
            val percent = Math.abs(verticalOffset * 1.0f) / appbarlayout.totalScrollRange //滑动比例
            if (percent > 0.8) {
                tvTitle!!.visibility = View.VISIBLE
                tvFocus!!.visibility = View.VISIBLE
                val alpha = 1 - (1 - percent) * 5 //渐变变换
                tvTitle!!.alpha = alpha
                tvFocus!!.alpha = alpha
            } else {
                tvTitle!!.visibility = View.GONE
                tvFocus!!.visibility = View.GONE
            }
        })
    }

    /**
     * 自动回顶部
     */
    private fun coordinatorLayoutBackTop() {
        val behavior = (appbarlayout!!.layoutParams as CoordinatorLayout.LayoutParams).behavior
        if (behavior is AppBarLayout.Behavior) {
            val appbarlayoutBehavior = behavior
            val topAndBottomOffset = appbarlayoutBehavior.topAndBottomOffset
            if (topAndBottomOffset != 0) {
                appbarlayoutBehavior.topAndBottomOffset = 0
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ivReturn -> RxBus.getDefault().post(MainPageChangeEvent(0))
            R.id.iv_head -> transitionAnim(ivHead, curUserBean!!.head)
            R.id.ivBg -> {
            }
            R.id.llFocus -> startActivity(Intent(activity, FocusActivity::class.java))
            R.id.llFans -> startActivity(Intent(activity, FocusActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (subscription != null) {
            subscription!!.unsubscribe()
        }
    }
}