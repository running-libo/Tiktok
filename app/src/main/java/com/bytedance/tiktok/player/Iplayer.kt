package com.bytedance.tiktok.player

import com.google.android.exoplayer2.ExoPlayer

interface Iplayer {

    fun playVideo(url: String)

    fun getplayer(): ExoPlayer

    fun play()

    fun pause()

    fun stop()

    fun release()

    fun isPlaying(): Boolean
}