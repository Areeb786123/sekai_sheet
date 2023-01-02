package com.areeb.sekaisheet.ui.Home.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDtoItem
import com.areeb.sekaisheet.utils.setImageView
import com.example.sekaisheet.databinding.HomeItemBinding

class HomeViewHolder(private val bindingAdapter: HomeItemBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root) {

    lateinit var wallpaperResponseDto: WallpaperResponseDtoItem

    fun bind(wallpaperDto: WallpaperResponseDtoItem) {
        this.wallpaperResponseDto = wallpaperDto
        setImageView(bindingAdapter.homeItemImageView, wallpaperResponseDto.urls.raw)
    }
}
