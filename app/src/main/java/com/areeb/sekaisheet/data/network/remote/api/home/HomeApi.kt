package com.areeb.sekaisheet.data.network.remote.api.home

import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeApi {
    companion object {
        private const val client_id = "tnV-FAsxWgslcb_CTGIWKQoifqnUM8iG71HO8nTiz3M"
    }

    @GET("photos/?client_id=$client_id")
    suspend fun getWallpaper(): WallpaperResponseDto
}
