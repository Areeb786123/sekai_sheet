package com.areeb.sekaisheet.ui.Home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.ui.Home.viewHolder.HomeViewHolder
import com.areeb.sekaisheet.ui.common.diffCallBack.DiffCallBack
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.example.sekaisheet.databinding.HomeItemBinding

class HomeAdapter(
    private val clickListener: ItemClickListener<WallpaperUnSplashDtoItem>
) :
    PagingDataAdapter<WallpaperUnSplashDtoItem, HomeViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(
                it,
                clickListener
            )
        }
    }
}