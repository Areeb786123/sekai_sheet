package com.areeb.sekaisheet.ui.Category.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.sekaisheet.data.models.collectionsModel.CollectionDto
import com.areeb.sekaisheet.ui.Category.viewHolder.CollectionViewHolder

class CollectionAdapter(private val collectionList: List<CollectionDto>) :
    RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: CollectionViewHolder, position: Int) {
        viewHolder.bind(collectionList[position])
    }

    override fun getItemCount() = collectionList.size
}