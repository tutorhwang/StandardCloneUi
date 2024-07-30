package com.example.standardcloneui.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.standardcloneui.presentation.ListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val PREF_FAVORITES = "pref_favorites"

class FavoriteRepository(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_FAVORITES, Context.MODE_PRIVATE)
    private val gson = Gson()
    private val _favoriteItems = loadFavorites()
    val favoriteItems: List<ListItem.VideoItem>
        get() = _favoriteItems.toList()

    fun addFavoriteItem(video: ListItem.VideoItem) {
        if (!_favoriteItems.contains(video)) {
            _favoriteItems.add(0, video)
            saveFavorites()
        }
    }

    fun removeFavoriteItem(video: ListItem.VideoItem) {
        _favoriteItems.remove(video)
        saveFavorites()
    }

    private fun saveFavorites() {
        val json = gson.toJson(_favoriteItems)
        sharedPreferences.edit().putString("favorite_items", json).apply()
    }

    private fun loadFavorites(): MutableList<ListItem.VideoItem> {
        val json = sharedPreferences.getString("favorite_items", null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<ListItem.VideoItem>>() {}.type
        return gson.fromJson(json, type)
    }
}