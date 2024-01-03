package com.areeb.sekaisheet.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.net.toUri
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.areeb.sekaisheet.R

@SuppressLint("CheckResult")
fun setImageView(imageView: ImageView, imageUrl: String?, lotteAnimation: LottieAnimationView?) {
    imageView.let {
        lotteAnimation?.visible(true)
        val imageUri = imageUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imageView.context)
            .load(imageUri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    lotteAnimation?.visible(true)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    lotteAnimation?.visible(false)
                    return false
                }
            })
            .apply {
                RequestOptions()
                    .error(R.drawable.not_able_to_relocate)
            }
            .into(imageView)
    }
}
