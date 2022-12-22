package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto

interface IRemoteOperations {
    suspend fun getWallpapers(): Resource<WallpaperResponseDto>
}
