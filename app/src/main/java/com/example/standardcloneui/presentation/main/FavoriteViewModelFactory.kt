package com.example.standardcloneui.presentation.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.standardcloneui.data.repository.FavoriteRepository

class FavoriteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(FavoriteRepository(context.applicationContext)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}