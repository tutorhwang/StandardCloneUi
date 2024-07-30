package com.example.standardcloneui.data.remote

import com.example.standardcloneui.BuildConfig
import com.example.standardcloneui.data.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_MAX_RESULT = 20
private const val API_REGION = "US"
private const val API_KEY = BuildConfig.YOUTUBE_API_KEY

interface YoutubeAPI {
    @GET("videos")
    suspend fun getTrendingVideos(
        @Query("part") part: String = "snippet",
        @Query("chart") chart: String = "mostPopular",
        @Query("maxResults") maxResults: Int = API_MAX_RESULT,
        @Query("regionCode") regionCode: String = API_REGION,
        @Query("key") apiKey: String = API_KEY
    ): VideoResponse
}