package com.bytedance.tiktok.player

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.bytedance.tiktok.activity.MainActivity
import com.bytedance.tiktok.databinding.ViewPlayviewBinding
import com.bytedance.tiktok.fragment.MainFragment
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.cache.SimpleCache

/**
 * create by libo
 * create on 2018/12/20
 * description 播放器VideoPlayer
 */
class VideoPlayer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs), Iplayer , DefaultLifecycleObserver {

    private val trackSelector: TrackSelector = DefaultTrackSelector(context)
    private val mPlayer : SimpleExoPlayer by lazy {
            SimpleExoPlayer.Builder(context)
        //            .setMediaSourceFactory(mediaSourceFactory)
                .setTrackSelector(trackSelector)
                .build()
    }
    private var binding: ViewPlayviewBinding = ViewPlayviewBinding.inflate(LayoutInflater.from(context), this, true)
//
//    private val simpleCache: SimpleCache by lazy {
//        SimpleCache(context.cacheDir, LastRe, ExoDatabaseProvider())
//    }

    init {
        initPlayer()
        initCache()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        //返回时，推荐页面可见，则继续播放视频
        if (MainActivity.curMainPage == 0 && MainFragment.Companion.curPage == 1) {
            play()
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)

        pause()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)

        stop()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)

        release()
    }

    private fun initPlayer() {

//        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(
//            ExoDownloadTool.getInstance(context).buildVideoCacheFactory()
//        )
        binding.playerview.player = mPlayer
        binding.playerview.useController = false
        mPlayer.playWhenReady = true
        mPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    private fun initCache() {
//        DefaultHlsDataSourceFactory(DefaultHttpDataSourceFactory())
    }

    override fun playVideo(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        val mediaItem = MediaItem.fromUri(url)
        mPlayer.setMediaItem(mediaItem)
        mPlayer.prepare()
        mPlayer.play()
    }

    override fun getplayer(): SimpleExoPlayer {
        return mPlayer
    }

    override fun pause() {
        mPlayer.pause()
    }

    override fun play() {
        mPlayer.play()
    }

    override fun stop() {
        mPlayer.stop()
    }

    override fun release() {
        mPlayer?.let {
            it.release()
        }
    }

    override fun isPlaying(): Boolean = mPlayer.isPlaying
}