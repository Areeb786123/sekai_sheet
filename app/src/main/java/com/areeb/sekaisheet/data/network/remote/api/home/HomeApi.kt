package com.areeb.sekaisheet.data.network.remote.api.home

import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDto
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.utils.Constants.ApiObjects.Companion.CLIENT_ID
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

//    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos/?client_id=$CLIENT_ID")
    suspend fun getWallpaper(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): WallpaperUnSplashDto

    @GET("photos/{id}/?client_id=$CLIENT_ID")
    suspend fun getWallpaperById(@Path("id") type: String): WallpaperUnSplashDtoItem
}
