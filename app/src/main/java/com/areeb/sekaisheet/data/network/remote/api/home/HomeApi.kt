package com.areeb.sekaisheet.data.network.remote.api.home

import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("photos")
    suspend fun getWallpaper(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): WallpaperUnSplashDto
}
