package com.areeb.sekaisheet.ui.Home.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi

class HomePaginationSource(
    private val query: String,
    private val homeApi: HomeApi
) :
    PagingSource<Int, WallpaperUnSplashDtoItem>() {
    override fun getRefreshKey(state: PagingState<Int, WallpaperUnSplashDtoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WallpaperUnSplashDtoItem> {
        return try {
            val position = params.key ?: 1
            val lastPosition = params.loadSize
            val response = homeApi.getWallpaper(query, position, lastPosition)
            return LoadResult.Page(
                data = response.results.filter { !it.premium },
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.results.isEmpty()) {
                    null
                } else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
