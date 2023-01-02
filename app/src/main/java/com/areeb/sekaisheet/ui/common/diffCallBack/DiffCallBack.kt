package com.areeb.sekaisheet.ui.common.diffCallBack

import androidx.recyclerview.widget.DiffUtil
import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDtoItem

class DiffCallBack : DiffUtil.ItemCallback<WallpaperResponseDtoItem>() {
    override fun areItemsTheSame(
        oldItem: WallpaperResponseDtoItem,
        newItem: WallpaperResponseDtoItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WallpaperResponseDtoItem,
        newItem: WallpaperResponseDtoItem
    ): Boolean {
        return oldItem.id == newItem.id
    }
}