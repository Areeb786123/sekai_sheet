package com.areeb.sekaisheet.ui.common.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem

class DiffCallBack : DiffUtil.ItemCallback<WallpaperUnSplashDtoItem>() {
    override fun areItemsTheSame(
        oldItem: WallpaperUnSplashDtoItem,
        newItem: WallpaperUnSplashDtoItem,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WallpaperUnSplashDtoItem,
        newItem: WallpaperUnSplashDtoItem,
    ): Boolean {
        return oldItem.id == newItem.id
    }
}
