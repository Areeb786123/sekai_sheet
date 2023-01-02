package com.areeb.sekaisheet.utils

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sekaisheet.R

fun setImageView(imageView: ImageView, imageUrl: String?) {
    imageView.let {
        val imageUri = imageUrl?.toUri()?.buildUpon()?.scheme("https")?.build()
        Glide.with(imageView.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.anim_loading)
                    .error(R.drawable.anim_error)
            )
            .into(imageView)
    }
}