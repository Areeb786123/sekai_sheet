package com.areeb.sekaisheet.ui.Category.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.areeb.sekaisheet.data.models.collectionsModel.CollectionDto
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.areeb.sekaisheet.utils.setImageView
import com.areeb.sekaisheet.databinding.CollectionsItemsBinding

class CollectionViewHolder(private val bindingAdapter: CollectionsItemsBinding) :
    RecyclerView.ViewHolder(bindingAdapter.root), View.OnClickListener {

    init {
        bindingAdapter.collectionImageView.setOnClickListener(this)
    }

    private lateinit var itemClick: ItemClickListener<CollectionDto>

    private lateinit var collections: CollectionDto

    fun bind(collectionsDto: CollectionDto, itemClickListener: ItemClickListener<CollectionDto>) {
        this.collections = collectionsDto
        this.itemClick = itemClickListener

        bindingAdapter.collectionTextView.text = collectionsDto.title
        setImageView(
            bindingAdapter.collectionImageView,
            collectionsDto.img_url,
            bindingAdapter.progress,
        )
    }

    companion object {
        fun from(parent: ViewGroup): CollectionViewHolder {
            return CollectionViewHolder(
                CollectionsItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                ),
            )
        }
    }

    override fun onClick(view: View?) {
        itemClick.onClick(collections)
    }
}
