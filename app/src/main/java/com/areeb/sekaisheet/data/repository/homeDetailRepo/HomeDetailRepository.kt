package com.areeb.sekaisheet.data.repository.homeDetailRepo

import com.areeb.sekaisheet.data.RemoteOperations
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeDetailRepository @Inject constructor(
    private val remoteOperations: RemoteOperations
) {
    fun getWallpaperById(wallpaperId: String): Flow<Resource<WallpaperUnSplashDtoItem>> {
        return flow {
            val wallpaperResponse = remoteOperations.getWallpaperById(wallpaperId)
            emit(wallpaperResponse)
        }.flowOn(Dispatchers.IO)
    }
}
