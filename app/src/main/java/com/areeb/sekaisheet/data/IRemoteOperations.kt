package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem

interface IRemoteOperations {
    suspend fun getWallpapers(): Resource<WallpaperUnSplashDtoItem>
}
