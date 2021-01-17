package com.songs.ui.custom_player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.songs.R
import com.songs.base.BaseActivity
import com.songs.constants.AppConstants
import com.songs.databinding.ActivityPlayerBinding
import com.songs.utils.PrintLog

class PlayerActivity : BaseActivity<ActivityPlayerBinding>(), Player.EventListener {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var url: String
    private var player: SimpleExoPlayer? = null
    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReadyState = true
    private var fullScreenFlag = false
    private lateinit var btnFullScreen: AppCompatImageView
    private lateinit var btnClose: AppCompatImageView

    override fun getLayoutId(): Int {
        return R.layout.activity_player
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        btnFullScreen = binding.videoView.findViewById(R.id.btn_exo_fullscreen)
        btnClose = binding.videoView.findViewById(R.id.btn_exo_close)
        getBundleData()
        initListener()
    }

    private fun getBundleData() {
        intent.extras?.let {
            url = it.getString(AppConstants.BundleConstants.KEY_DATA)!!
        }
    }

    private fun initListener() {
        btnFullScreen.setOnClickListener {
            if (fullScreenFlag) {
                fullScreenFlag = false
                btnFullScreen.setImageResource(R.drawable.ic_exo_fullscreen)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else {
                fullScreenFlag = true
                btnFullScreen.setImageResource(R.drawable.ic_exo_fullscreen_exit)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
            hideSystemUi()
        }
        btnClose.setOnClickListener {
            finishActivityWithExitDownTransition()
        }
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build().also {
            binding.videoView.player = it
            val mediaSource = buildMediaSource(Uri.parse(url))
            it.playWhenReady = playWhenReadyState
            it.seekTo(currentWindow, playbackPosition)
            it.prepare(mediaSource, false, false)
            it.addListener(this)
        }
        if (playWhenReadyState) {
            binding.pbVideo.visibility = View.VISIBLE
        } else {
            binding.pbVideo.visibility = View.GONE
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(this, "women_community")
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        player?.let {
            playWhenReadyState = it.playWhenReady
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            it.release()
            player = null
        }
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                binding.pbVideo.visibility = View.VISIBLE
            }
            Player.STATE_ENDED -> {
                binding.pbVideo.visibility = View.GONE
            }
            Player.STATE_IDLE -> {
                player?.retry()
            }
            Player.STATE_READY -> {
                binding.pbVideo.visibility = View.GONE
            }
            else -> {
            }
        }
    }

    override fun onPlayerError(error: ExoPlaybackException) {
        when (error.type) {
            ExoPlaybackException.TYPE_SOURCE -> {
                PrintLog.e(
                    PlayerActivity::class.java.simpleName,
                    "TYPE_SOURCE: " + error.sourceException.message
                )
            }
            ExoPlaybackException.TYPE_RENDERER -> PrintLog.e(
                PlayerActivity::class.java.simpleName,
                "TYPE_RENDERER: " + error.rendererException.message
            )
            ExoPlaybackException.TYPE_UNEXPECTED -> PrintLog.e(
                PlayerActivity::class.java.simpleName,
                "TYPE_UNEXPECTED: " + error.unexpectedException.message
            )
            ExoPlaybackException.TYPE_OUT_OF_MEMORY -> PrintLog.e(
                PlayerActivity::class.java.simpleName,
                "TYPE_UNEXPECTED: " + error.unexpectedException.message
            )
            ExoPlaybackException.TYPE_REMOTE -> PrintLog.e(
                PlayerActivity::class.java.simpleName,
                "TYPE_REMOTE: " + error.unexpectedException.message
            )
        }
    }


    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    override fun onBackPressed() {
        finishActivityWithExitDownTransition()
    }
}