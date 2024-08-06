package com.example.standardcloneui.data.repository

import com.example.standardcloneui.data.model.ai.OpenAIRequest
import com.example.standardcloneui.data.model.ai.OpenAIResponse
import com.example.standardcloneui.network.RetrofitClient

class AiRepository {
    suspend fun createChatCompletion(request: OpenAIRequest): OpenAIResponse {
        return RetrofitClient.openAiApi.createChatCompletion(request)
    }
}