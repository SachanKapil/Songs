package com.songs.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by {Kapil Sachan} on 17/01/2021.
 */

object ViewUtils {

    fun loadImage(
        ivImage: ImageView,
        pbLoader: ProgressBar?,
        placeholderResourceId: Int?,
        imageUrl: String,
        cornerRadiusInDP: Int,
        isCenterCrop: Boolean,
    ) {

        var requestBuilder = Glide.with(ivImage.context).load(imageUrl)
        pbLoader?.visibility = View.VISIBLE
        requestBuilder = requestBuilder.listener(object : RequestListener<Drawable?> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any,
                target: Target<Drawable?>,
                isFirstResource: Boolean
            ): Boolean {
                pbLoader?.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any,
                target: Target<Drawable?>,
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

        requestBuilder = if (isCenterCrop) {
            requestBuilder.transform(
                CenterCrop(),
                RoundedCorners(
                    AppUtils.convertDpToPixel(cornerRadiusInDP.toFloat(), ivImage.context).toInt()
                )
            )
        } else {
            requestBuilder.transform(
                RoundedCorners(
                    AppUtils.convertDpToPixel(cornerRadiusInDP.toFloat(), ivImage.context).toInt()
                )
            )
        }

        requestBuilder.into(ivImage)
    }
}