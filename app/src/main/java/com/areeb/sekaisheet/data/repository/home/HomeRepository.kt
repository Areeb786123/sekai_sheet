package com.areeb.sekaisheet.data.repository.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import com.areeb.sekaisheet.ui.Home.pagination.HomePaginationSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi
) {

//    fun getAllWallpapers(): Flow<Resource<WallpaperResponseDto>> {
//        Log.e("repoCalled", "Home RepoCalled")
//        return flow {
//            val wallpaperResponse = remoteOperations.getWallpapers()
//            emit(wallpaperResponse)
//        }.flowOn(Dispatchers.IO)
//    }

    fun getAllWallpapers() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { HomePaginationSource(homeApi) }
    ).liveData
}
