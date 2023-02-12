package com.areeb.sekaisheet.data.models.collectionsModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionDto(
    val img_url: String,
    val title: String
) :Parcelable