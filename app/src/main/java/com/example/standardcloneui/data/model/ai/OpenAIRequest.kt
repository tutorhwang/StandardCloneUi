package com.example.standardcloneui.data.model.ai

import com.example.standardcloneui.data.remote.AI_MODEL

data class OpenAIRequest(
    val model: String = AI_MODEL,
    val messages: List<Message>,
//    val temperature: Double? = null,
//    val maxTokens: Int? = null,
//    val frequencyPenalty: Double? = null,
//    val presencePenalty: Double? = null
)
