package com.example.standardcloneui.presentation.ai

sealed class AIResultUIState {
    object Loading : AIResultUIState()
    data class Success(val response: AIResponse) : AIResultUIState()
    data class Error(val errorMessage: String) : AIResultUIState()
}

data class AIResponse(val aiMessage: String)