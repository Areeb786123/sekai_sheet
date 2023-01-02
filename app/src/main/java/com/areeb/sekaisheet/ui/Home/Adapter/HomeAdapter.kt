package com.areeb.sekaisheet.ui.Home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDtoItem
import com.areeb.sekaisheet.ui.Home.viewHolder.HomeViewHolder
import com.areeb.sekaisheet.ui.common.diffCallBack.DiffCallBack
import com.example.sekaisheet.databinding.HomeItemBinding

class HomeAdapter() : ListAdapter<WallpaperResponseDtoItem, HomeViewHolder>(DiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        viewHolder.bind(
            getItem(position)
        )
    }
}