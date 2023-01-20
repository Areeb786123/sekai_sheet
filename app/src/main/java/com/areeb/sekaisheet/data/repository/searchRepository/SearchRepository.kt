package com.areeb.sekaisheet.data.repository.searchRepository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import com.areeb.sekaisheet.ui.Home.pagination.HomePaginationSource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val homeApi: HomeApi
) {

    fun getSearchedWallpaper(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { HomePaginationSource(query, homeApi) }
    ).liveData
}