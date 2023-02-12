package com.areeb.sekaisheet.ui.Category.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.sekaisheet.utils.setImageView
import com.example.sekaisheet.databinding.CollectionsItemsBinding

class CollectionViewHolder(private val bindingAdapter: CollectionsItemsBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root) {

    private lateinit var collections: com.areeb.sekaisheet.data.models.collectionsModel.CollectionDto
    fun bind(collectionsDto: com.areeb.sekaisheet.data.models.collectionsModel.CollectionDto) {
        this.collections = collectionsDto

        bindingAdapter.collectionTextView.text = collectionsDto.title
        setImageView(
            bindingAdapter.collectionImageView,
            collectionsDto.img_url,
            bindingAdapter.progress
        )
    }

    companion object {
        fun from(parent: ViewGroup): CollectionViewHolder {
            return CollectionViewHolder(
                CollectionsItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}