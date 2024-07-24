package com.example.standardcloneui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.standardcloneui.data.ListItem
import com.example.standardcloneui.repository.FavoriteRepository

class MainViewModel(private val repository: FavoriteRepository = FavoriteRepository()) :
    ViewModel() {
    private val _favoriteList: MutableLiveData<List<ListItem.VideoItem>> = MutableLiveData()
    val favoriteList: LiveData<List<ListItem.VideoItem>> = _favoriteList

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        _favoriteList.value = repository.favoriteItems
    }

    fun addFavoriteItem(video: ListItem.VideoItem) {
        repository.addFavoriteItem(video)
        _favoriteList.value = repository.favoriteItems
    }

    fun removeFavoriteItem(video: ListItem.VideoItem) {
        repository.removeFavoriteItem(video)
        _favoriteList.value = repository.favoriteItems
    }
}
