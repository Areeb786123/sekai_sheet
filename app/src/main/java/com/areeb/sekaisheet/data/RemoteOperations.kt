package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteOperations @Inject constructor(
    private val homeApi: HomeApi
) : IRemoteOperations, SafeApiCall {
    override suspend fun getWallpaperById(id: String): Resource<WallpaperUnSplashDtoItem> {
        return safeApiCall { homeApi.getWallpaperById(id) }
    }
}
