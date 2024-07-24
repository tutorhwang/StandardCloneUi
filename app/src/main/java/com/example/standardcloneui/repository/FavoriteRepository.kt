package com.example.standardcloneui.repository

import com.example.standardcloneui.data.ListItem

class FavoriteRepository {
    private val _favoriteItems = mutableListOf<ListItem.VideoItem>()
    val favoriteItems: List<ListItem.VideoItem>
        get() = _favoriteItems.toList()

    fun addFavoriteItem(video: ListItem.VideoItem) {
        if (!_favoriteItems.contains(video)) {
            _favoriteItems.add(0, video)
        }
    }

    fun removeFavoriteItem(video: ListItem.VideoItem) {
        _favoriteItems.remove(video)
    }
}