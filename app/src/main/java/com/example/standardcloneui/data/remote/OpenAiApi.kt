package com.example.standardcloneui.data.remote

import com.example.standardcloneui.BuildConfig
import com.example.standardcloneui.data.model.ai.OpenAIRequest
import com.example.standardcloneui.data.model.ai.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val API_KEY = BuildConfig.OPEN_AI_API_KEY
const val AI_MODEL: String =  "gpt-4o-mini"

interface OpenAIApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer $API_KEY"
    )
    @POST("chat/completions")
    suspend fun createChatCompletion(@Body request: OpenAIRequest): OpenAIResponse
}