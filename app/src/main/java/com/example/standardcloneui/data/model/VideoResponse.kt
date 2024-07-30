package com.example.standardcloneui.data.model


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("nextPageToken")
    val nextPageToken: String?,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo?
)
data class Item(
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("snippet")
    val snippet: Snippet?
)

data class Snippet(
    @SerializedName("categoryId")
    val categoryId: String?,
    @SerializedName("channelId")
    val channelId: String?,
    @SerializedName("channelTitle")
    val channelTitle: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String?,
    @SerializedName("localized")
    val localized: Localized?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("thumbnails")
    val thumbnails: Thumbnails?,
    @SerializedName("title")
    val title: String?
)