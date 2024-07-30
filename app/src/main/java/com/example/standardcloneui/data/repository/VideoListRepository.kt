package com.example.standardcloneui.data.repository

import com.example.standardcloneui.data.model.VideoResponse

interface VideoRepository {
    suspend fun getTrendingVideos(region: String): VideoResponse

}