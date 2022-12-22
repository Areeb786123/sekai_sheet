package com.areeb.sekaisheet.data.network.remote.api.home

import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import retrofit2.http.GET

interface HomeApi {

    @GET("posts")
    suspend fun getWallpaper(): WallpaperResponseDto
}
