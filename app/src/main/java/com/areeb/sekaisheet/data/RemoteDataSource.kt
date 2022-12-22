package com.areeb.sekaisheet.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val networkInterceptor: NetworkConnectionInterceptor
) {
    private fun getOkHttpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .also {
                            if (apiKey.isNotEmpty()) {
                                it.addHeader("Accept-Version", "v1")
                                it.addHeader("Authorization", apiKey)
                            }
                        }.build()
                )
            }.addInterceptor(networkInterceptor)
            .build()
    }

    fun <Api> buildApi(api: Class<Api>, apiKey: String, baseUrl: String): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient(apiKey))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
