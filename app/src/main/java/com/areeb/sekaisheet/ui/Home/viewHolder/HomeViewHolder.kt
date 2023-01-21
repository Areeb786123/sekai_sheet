package com.areeb.sekaisheet.ui.Home.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.areeb.sekaisheet.utils.setImageView
import com.example.sekaisheet.databinding.HomeItemBinding

class HomeViewHolder(private val bindingAdapter: HomeItemBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root), View.OnClickListener {

    init {
        bindingAdapter.homeItemImageView.setOnClickListener(this)
    }

    lateinit var wallpaperResponseDto: WallpaperUnSplashDtoItem
    lateinit var clickListener: ItemClickListener<WallpaperUnSplashDtoItem>

    fun bind(
        wallpaperDto: WallpaperUnSplashDtoItem,
        clickListener: ItemClickListener<WallpaperUnSplashDtoItem>
    ) {
        this.wallpaperResponseDto = wallpaperDto
        this.clickListener = clickListener

        setImageView(bindingAdapter.homeItemImageView, wallpaperResponseDto.urls.regular, bindingAdapter.progress)
    }

    override fun onClick(view: View?) {
        clickListener.onClick(wallpaperResponseDto)
    }
}
