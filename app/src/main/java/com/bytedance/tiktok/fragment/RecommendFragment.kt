package com.bytedance.tiktok.fragment

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.MainActivity
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.adapter.VideoAdapter
import com.bytedance.tiktok.base.BaseFragment
import com.bytedance.tiktok.bean.CurUserBean
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.utils.OnVideoControllerListener
import com.bytedance.tiktok.utils.RxBus
import com.bytedance.tiktok.view.*
import com.bytedance.tiktok.view.viewpagerlayoutmanager.OnViewPagerListener
import com.bytedance.tiktok.view.viewpagerlayoutmanager.ViewPagerLayoutManager
import kotlinx.android.synthetic.main.fragment_recommend.*
import rx.functions.Action1

/**
 * create by libo
 * create on 2020-05-19
 * description 推荐播放页
 */
class RecommendFragment : BaseFragment() {
    private var adapter: VideoAdapter? = null
    private var viewPagerLayoutManager: ViewPagerLayoutManager? = null

    /** 当前播放视频位置  */
    private var curPlayPos = -1
    private var videoView: FullScreenVideoView? = null

    private var ivCurCover: ImageView? = null

    override fun setLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    override fun init() {
        adapter = VideoAdapter(activity, DataCreate.datas)
        recyclerView!!.adapter = adapter
        videoView = FullScreenVideoView(activity)
        setViewPagerLayoutManager()
        setRefreshEvent()

        //监听播放或暂停事件
        val subscribe = RxBus.getDefault().toObservable(PauseVideoEvent::class.java)
                .subscribe(Action1 { event: PauseVideoEvent ->
                    if (event.isPlayOrPause) {
                        videoView!!.start()
                    } else {
                        videoView!!.pause()
                    }
                } as Action1<PauseVideoEvent>)
//        subscribe.unsubscribe()
    }

    override fun onResume() {
        super.onResume()

        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.Companion.curPage == 1) {
            videoView!!.start()
        }
    }

    override fun onPause() {
        super.onPause()
        videoView!!.pause()
    }

    override fun onStop() {
        super.onStop()
        videoView!!.stopPlayback()
    }

    private fun setViewPagerLayoutManager() {
        viewPagerLayoutManager = ViewPagerLayoutManager(activity)
        recyclerView!!.layoutManager = viewPagerLayoutManager
        recyclerView!!.scrollToPosition(PlayListActivity.initPos)
        viewPagerLayoutManager!!.setOnViewPagerListener(object : OnViewPagerListener {
            override fun onInitComplete() {
                playCurVideo(PlayListActivity.initPos)
            }

            override fun onPageRelease(isNext: Boolean, position: Int) {
                if (ivCurCover != null) {
                    ivCurCover!!.visibility = View.VISIBLE
                }
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {
                playCurVideo(position)
            }
        })
    }

    private fun setRefreshEvent() {
        refreshLayout!!.setColorSchemeResources(R.color.color_link)
        refreshLayout!!.setOnRefreshListener {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    refreshLayout!!.isRefreshing = false
                }
            }.start()
        }
    }

    private fun playCurVideo(position: Int) {
        if (position == curPlayPos) {
            return
        }
        val itemView = viewPagerLayoutManager!!.findViewByPosition(position) ?: return
        val rootView = itemView.findViewById<ViewGroup>(R.id.rl_container)
        val likeView: LikeView = rootView.findViewById(R.id.likeview)
        val controllerView: ControllerView = rootView.findViewById(R.id.controller)
        val ivPlay = rootView.findViewById<ImageView>(R.id.iv_play)
        val ivCover = rootView.findViewById<ImageView>(R.id.iv_cover)
        ivPlay.alpha = 0.4f

        //播放暂停事件
        likeView.setOnPlayPauseListener(object: LikeView.OnPlayPauseListener {
            override fun onPlayOrPause() {
                if (videoView!!.isPlaying) {
                    videoView!!.pause()
                    ivPlay.visibility = View.VISIBLE
                } else {
                    videoView!!.start()
                    ivPlay.visibility = View.GONE
                }
            }

        })

        //评论点赞事件
        likeShareEvent(controllerView)

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(CurUserBean(DataCreate.datas[position].userBean!!))
        curPlayPos = position

        //切换播放器位置
        dettachParentView(rootView)
        autoPlayVideo(curPlayPos, ivCover)
    }

    /**
     * 移除videoview父view
     */
    private fun dettachParentView(rootView: ViewGroup) {
        //1.添加videoview到当前需要播放的item中,添加进item之前，保证ijkVideoView没有父view
        videoView?.parent?.let {
            (it as ViewGroup).removeView(videoView)
        }
        rootView.addView(videoView, 0)
    }

    /**
     * 自动播放视频
     */
    private fun autoPlayVideo(position: Int, ivCover: ImageView) {
        val bgVideoPath = "android.resource://" + activity!!.packageName + "/" + DataCreate.datas[position].videoRes
        videoView!!.setVideoPath(bgVideoPath)
        videoView!!.start()
        videoView!!.setOnPreparedListener { mp: MediaPlayer ->
            mp.isLooping = true

            //延迟取消封面，避免加载视频黑屏
            object : CountDownTimer(200, 200) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    ivCover.visibility = View.GONE
                    ivCurCover = ivCover
                }
            }.start()
        }
    }

    /**
     * 用户操作事件
     */
    private fun likeShareEvent(controllerView: ControllerView) {
        controllerView.setListener(object : OnVideoControllerListener {
            override fun onHeadClick() {
                RxBus.getDefault().post(MainPageChangeEvent(1))
            }

            override fun onLikeClick() {}
            override fun onCommentClick() {
                val commentDialog = CommentDialog()
                commentDialog.show(childFragmentManager, "")
            }

            override fun onShareClick() {
                ShareDialog().show(childFragmentManager, "")
            }
        })
    }
}