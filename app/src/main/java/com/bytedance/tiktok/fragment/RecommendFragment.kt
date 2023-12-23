package com.bytedance.tiktok.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bytedance.tiktok.R
import com.bytedance.tiktok.activity.PlayListActivity
import com.bytedance.tiktok.adapter.VideoAdapter
import com.bytedance.tiktok.base.BaseBindingFragment
import com.bytedance.tiktok.bean.CurUserBean
import com.bytedance.tiktok.bean.DataCreate
import com.bytedance.tiktok.bean.MainPageChangeEvent
import com.bytedance.tiktok.bean.PauseVideoEvent
import com.bytedance.tiktok.databinding.FragmentRecommendBinding
import com.bytedance.tiktok.utils.OnVideoControllerListener
import com.bytedance.tiktok.utils.RxBus
import com.bytedance.tiktok.view.CommentDialog
import com.bytedance.tiktok.view.ControllerView
import com.bytedance.tiktok.player.VideoPlayer
import com.bytedance.tiktok.view.LikeView
import com.bytedance.tiktok.view.ShareDialog
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import rx.Subscription
import rx.functions.Action1


/**
 * create by libo
 * create on 2020-05-19
 * description 推荐播放页
 */
class RecommendFragment : BaseBindingFragment<FragmentRecommendBinding>({FragmentRecommendBinding.inflate(it)}) {
    private var adapter: VideoAdapter?= null

    /** 当前播放视频位置  */
    private var curPlayPos = -1
    private lateinit var videoView: VideoPlayer

    private var ivCurCover: ImageView? = null
    private var subscribe: Subscription?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initVideoPlayer()
        setViewPagerLayoutManager()
        setRefreshEvent()
        observeEvent()
    }

    private fun initRecyclerView() {
        adapter  = VideoAdapter(requireContext(), binding.recyclerView.getChildAt(0) as RecyclerView)
        binding.recyclerView.adapter = adapter
        adapter?.appendList(DataCreate.datas)
    }

    private fun initVideoPlayer() {
        var params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        videoView = VideoPlayer(requireActivity())
        videoView.layoutParams = params
        lifecycle.addObserver(videoView)
    }

    private fun observeEvent() {
        //监听播放或暂停事件
        subscribe = RxBus.getDefault().toObservable(PauseVideoEvent::class.java)
            .subscribe(Action1 { event: PauseVideoEvent ->
                if (event.isPlayOrPause) {
                    videoView!!.play()
                } else {
                    videoView!!.pause()
                }
            } as Action1<PauseVideoEvent>)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribe?.unsubscribe()
        binding.recyclerView.unregisterOnPageChangeCallback(pageChangeCallback)
    }

    private fun setViewPagerLayoutManager() {
        with(binding.recyclerView) {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            offscreenPageLimit = 1
            registerOnPageChangeCallback(pageChangeCallback)
            (binding.recyclerView.getChildAt(0) as RecyclerView).scrollToPosition(PlayListActivity.initPos)
        }
    }

    private val pageChangeCallback = object: OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            playCurVideo(position)
        }
    }

    private fun setRefreshEvent() {
        binding.refreshLayout.setColorSchemeResources(R.color.color_link)
        binding.refreshLayout.setOnRefreshListener {
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    binding.refreshLayout!!.isRefreshing = false
                }
            }.start()
        }
    }

    private fun playCurVideo(position: Int) {
        if (position == curPlayPos) {
            return
        }
        val itemView = adapter!!.getRootViewAt(position)
        val rootView = itemView!!.findViewById<ViewGroup>(R.id.rl_container)
        val likeView: LikeView = rootView.findViewById(R.id.likeview)
        val controllerView: ControllerView = rootView.findViewById(R.id.controller)
        val ivPlay = rootView.findViewById<ImageView>(R.id.iv_play)
        val ivCover = rootView.findViewById<ImageView>(R.id.iv_cover)

        //播放暂停事件
        likeView.setOnPlayPauseListener(object: LikeView.OnPlayPauseListener {
            override fun onPlayOrPause() {
                if (videoView!!.isPlaying()) {
                    videoView?.pause()
                    ivPlay.visibility = View.VISIBLE
                } else {
                    videoView?.play()
                    ivPlay.visibility = View.GONE
                }
            }

        })

        //评论点赞事件
        likeShareEvent(controllerView)

        //切换播放视频的作者主页数据
        RxBus.getDefault().post(CurUserBean(DataCreate.datas[position]?.userBean!!))
        curPlayPos = position

        //切换播放器位置
        dettachParentView(rootView)
        autoPlayVideo(curPlayPos, ivCover)
    }

    /**
     * 移除videoview父view
     */
    private fun dettachParentView(rootView: ViewGroup) {
        //1.添加videoView到当前需要播放的item中,添加进item之前，保证videoView没有父view
        videoView?.parent?.let {
            (it as ViewGroup).removeView(videoView)
        }

        rootView.addView(videoView, 0)
    }

    /**
     * 自动播放视频
     */
    private fun autoPlayVideo(position: Int, ivCover: ImageView) {
        videoView.playVideo(adapter!!.getDatas()[position].mediaSource!!)

        videoView.getplayer()?.addListener(object: Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                // 播放状态发生变化时的回调
                // 播放状态包括：Player.STATE_IDLE、Player.STATE_BUFFERING、Player.STATE_READY、Player.STATE_ENDED
                if (state == Player.STATE_READY) {

                }
            }

            fun onPlayerError(error: ExoPlaybackException?) {
                // 播放发生错误时的回调
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                // 播放状态变为播放或暂停时的回调
            }

            override fun onRenderedFirstFrame() {
                //第一帧已渲染，隐藏封面
                ivCover.visibility = View.GONE
                ivCurCover = ivCover
            }
        })
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