package com.example.standardcloneui.data.model


import com.google.gson.annotations.SerializedName

data class PageInfo(
    @SerializedName("resultsPerPage")
    val resultsPerPage: Int?,
    @SerializedName("totalResults")
    val totalResults: Int?
)