package com.example.standardclonui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val channelTitle: String,
    val title: String,
    val thumbnail: String,
    val description: String
): Parcelable