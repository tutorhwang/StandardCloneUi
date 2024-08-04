package com.example.standardcloneui.data.repository

import com.example.standardcloneui.data.model.video.VideoResponse
import com.example.standardcloneui.network.RetrofitClient

class YoutubeRepositoryImpl : VideoRepository {
    override suspend fun getTrendingVideos(region: String): VideoResponse {
        return RetrofitClient.youtubeAPI.getTrendingVideos(regionCode = region)
    }
}