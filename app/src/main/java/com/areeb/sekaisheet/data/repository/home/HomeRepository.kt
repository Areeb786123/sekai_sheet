package com.areeb.sekaisheet.data.repository.home

import android.util.Log
import com.areeb.sekaisheet.data.RemoteOperations
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteOperations: RemoteOperations
) {

    fun getAllWallpapers(): Flow<Resource<WallpaperResponseDto>> {
        Log.e("repoCalled", "Home RepoCalled")
        return flow {
            val wallpaperResponse = remoteOperations.getWallpapers()
            emit(wallpaperResponse)
        }.flowOn(Dispatchers.IO)
    }
}
