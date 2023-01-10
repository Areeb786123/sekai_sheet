package com.areeb.sekaisheet.di

import com.areeb.sekaisheet.data.RemoteDataSource
import com.areeb.sekaisheet.data.network.remote.api.home.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideHomeApi(
        remoteDataSource: RemoteDataSource
    ): HomeApi {
        return remoteDataSource.buildApi(
            HomeApi::class.java,
            "https://unsplash.com/napi/"
        )
    }
}
