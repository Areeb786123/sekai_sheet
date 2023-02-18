package com.areeb.sekaisheet.data.repository.home

import androidx.paging.*
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import com.areeb.sekaisheet.ui.Home.pagination.HomePaginationSource
import com.areeb.sekaisheet.utils.Constants.ApiObjects.Companion.TRENDING
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
) {
    // Trending will be default value because Unsplash didn't allow to use their base API ..
    fun getAllWallpapers(data: String?) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),

        pagingSourceFactory = {
            HomePaginationSource(
                data ?: TRENDING,
                homeApi,
            )
        },
    ).liveData
}
