package com.songs.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by {Kapil Sachan} on 17/01/2021.
 */

object ViewUtils {

    fun loadGif(
        ivImage: ImageView,
        pbLoader: ProgressBar?,
        placeholderResourceId: Int?,
        imageUrl: String
    ) {

        var requestBuilder = Glide.with(ivImage.context).asGif().load(imageUrl)
        pbLoader?.visibility = View.VISIBLE
        requestBuilder = requestBuilder.listener(object : RequestListener<GifDrawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<GifDrawable?>,
                isFirstResource: Boolean
            ): Boolean {
                pbLoader?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: GifDrawable?,
                model: Any,
                target: Target<GifDrawable?>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                pbLoader?.visibility = View.GONE
                return false
            }
        })

        placeholderResourceId?.let {
            requestBuilder.placeholder(placeholderResourceId)
        }

        requestBuilder.into(ivImage)
    }
}