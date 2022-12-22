package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteOperations @Inject constructor(
    private val homeApi: HomeApi
) : IRemoteOperations, SafeApiCall {
    override suspend fun getWallpapers(): Resource<WallpaperResponseDto> {
        return safeApiCall { homeApi.getWallpaper() }
    }
}
