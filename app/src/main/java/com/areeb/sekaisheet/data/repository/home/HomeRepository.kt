package com.areeb.sekaisheet.data.repository.home

import androidx.paging.*
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import com.areeb.sekaisheet.ui.Home.pagination.HomePaginationSource
import com.areeb.sekaisheet.utils.CollectionData.moodSingletonText
import com.areeb.sekaisheet.utils.Constants.ApiObjects.Companion.TRENDING
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
) {
    // Trending will be default value because Unsplash didn't allow to use their base API ..
    fun getAllWallpapers(data: String?, viewModelScope: CoroutineScope) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),

        pagingSourceFactory = {
            HomePaginationSource(
                data ?: moodSingletonText!!,
                homeApi,
            )
        },
    ).flow.cachedIn(viewModelScope)
}
