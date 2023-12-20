package com.bytedance.tiktok.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bytedance.tiktok.databinding.ViewPlayviewBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector

/**
 * create by libo
 * create on 2018/12/20
 * description 播放器VideoPlayer
 */
class VideoPlayer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    private val trackSelector: TrackSelector = DefaultTrackSelector(context)
    private val mPlayer : SimpleExoPlayer by lazy {
            SimpleExoPlayer.Builder(context)
        //            .setMediaSourceFactory(mediaSourceFactory)
                .setTrackSelector(trackSelector)
                .build()
    }
    private var binding: ViewPlayviewBinding = ViewPlayviewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initPlayer()
    }

    private fun initPlayer() {

//        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(
//            ExoDownloadTool.getInstance(context).buildVideoCacheFactory()
//        )
        binding.playerview.player = mPlayer
        mPlayer.playWhenReady = true
        mPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    fun playVideo(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }
        val mediaItem = MediaItem.fromUri(url)
        mPlayer.setMediaItem(mediaItem)
        mPlayer.prepare()
        mPlayer.play()
    }

    fun getplayer(): SimpleExoPlayer? {
        return mPlayer
    }

    fun pause() {
        mPlayer.pause()
    }

    fun play() {
        mPlayer.play()
    }

    fun stop() {
        mPlayer.stop()
    }

    fun releasePlayer() {
        mPlayer?.let {
            it.release()
        }
    }

    fun isPlaying(): Boolean = mPlayer.isPlaying
}