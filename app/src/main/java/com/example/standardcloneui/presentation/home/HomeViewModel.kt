package com.example.standardcloneui.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardcloneui.data.repository.VideoRepository
import com.example.standardcloneui.data.repository.YoutubeRepositoryImpl
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.presentation.toVideoItem
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

private const val TAG = "HomeViewModel"

class HomeViewModel(private val repository: VideoRepository = YoutubeRepositoryImpl()) :
    ViewModel() {
    private val _trendingVideos = MutableLiveData<List<ListItem.VideoItem>>()
    val trendingVideos: LiveData<List<ListItem.VideoItem>> = _trendingVideos

    fun fetchTrendingVideos(region: String = "US") {
        viewModelScope.launch {
            runCatching {
                val videos = repository.getTrendingVideos(region).items?.toVideoItem()
                _trendingVideos.value = videos
            }.onFailure {
                Log.e(TAG, "fetchTrendingVideos() failed! : ${it.message}")
                handleException(it)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is HttpException -> {
                val errorJsonString = e.response()?.errorBody()?.string()
                Log.e(TAG, "HTTP error: $errorJsonString")
            }

            is IOException -> Log.e(TAG, "Network error: $e")
            else -> Log.e(TAG, "Unexpected error: $e")
        }
    }
}