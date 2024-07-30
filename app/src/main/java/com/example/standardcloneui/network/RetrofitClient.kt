package com.example.standardcloneui.network

import com.example.standardcloneui.data.remote.YoutubeAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val youtubeAPI: YoutubeAPI by lazy { retrofit.create() }
}