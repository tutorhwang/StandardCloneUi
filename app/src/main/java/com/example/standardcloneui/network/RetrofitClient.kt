package com.example.standardcloneui.network

import com.example.standardcloneui.data.remote.OpenAIApi
import com.example.standardcloneui.data.remote.YoutubeAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
    val youtubeAPI: YoutubeAPI by lazy { createRetrofit(YOUTUBE_BASE_URL).create() }

    private const val OPEN_AI_BASE_URL = "https://api.openai.com/v1/"
    val openAiApi: OpenAIApi by lazy { createRetrofit(OPEN_AI_BASE_URL).create() }

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}