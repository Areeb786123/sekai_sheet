package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem

interface IRemoteOperations {

    suspend fun getWallpaperById(id: String): Resource<WallpaperUnSplashDtoItem>
}
