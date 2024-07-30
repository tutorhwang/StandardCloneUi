package com.example.standardcloneui.presentation

import com.example.standardcloneui.data.model.Item

fun List<Item>.toVideoItem(): List<ListItem.VideoItem> {
    return this.map {
        ListItem.VideoItem(
            channelTitle = it.snippet?.channelTitle ?: "",
            title = it.snippet?.title ?: "",
            thumbnail = it.snippet?.thumbnails?.high?.url ?: "",
            description = it.snippet?.description ?: ""
        )
    }
}