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
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.source.BaseMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
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
                .setTrackSelector(trackSelector)
//                .setLoadControl(loadControl) //构建内存缓冲
                .build()
    }

    // 自定义 DefaultLoadControl 参数
    val MIN_BUFFER_MS = 5_000 // 最小缓冲时间，
    val MAX_BUFFER_MS = 7_000 // 最大缓冲时间
    val PLAYBACK_BUFFER_MS = 700 // 最小播放缓冲时间，只有缓冲到达这个时间后才是可播放状态
    val REBUFFER_MS = 1_000 // 当缓冲用完，再次缓冲的时间
    val loadControl = DefaultLoadControl.Builder()
        .setPrioritizeTimeOverSizeThresholds(true)//缓冲时时间优先级高于大小
        .setBufferDurationsMs(MIN_BUFFER_MS, MAX_BUFFER_MS, PLAYBACK_BUFFER_MS, REBUFFER_MS)
        .build()

    private var binding: ViewPlayviewBinding = ViewPlayviewBinding.inflate(LayoutInflater.from(context), this, true)
    companion object {
        const val MAX_CACHE_BYTE: Long = 1024*1024*200  //200MB
    }

    init {
        initPlayer()
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
        binding.playerview.player = mPlayer
        binding.playerview.useController = false
        mPlayer.playWhenReady = true
        mPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    /**
     * 使用本地缓存播放
     */
    fun playVideo(mediaSource: BaseMediaSource) {
        mPlayer.setMediaSource(mediaSource)
        mPlayer.prepare()
        mPlayer.play()
    }


    /**
     * 构建一个共用缓存文件
     */
    val cache: SimpleCache by lazy {
        val cacheFile = context.cacheDir.resolve("tiktok_cache_file")
        SimpleCache(cacheFile, LeastRecentlyUsedCacheEvictor(MAX_CACHE_BYTE), StandaloneDatabaseProvider(context))
    }

    /**
     * 根据url生成缓存，播放本地缓存
     */
    override fun playVideo(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        val mediaItem = MediaItem.fromUri(url)
        val dataSourceFactory = CacheDataSource.Factory().setCache(cache).setUpstreamDataSourceFactory(
            DefaultDataSource.Factory(context))
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
        mPlayer.setMediaSource(mediaSource)
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