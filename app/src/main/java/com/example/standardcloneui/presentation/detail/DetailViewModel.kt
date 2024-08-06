package com.example.standardcloneui.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.standardcloneui.data.model.ai.Message
import com.example.standardcloneui.data.model.ai.OpenAIRequest
import com.example.standardcloneui.data.repository.AiRepository
import com.example.standardcloneui.presentation.ListItem
import com.example.standardcloneui.presentation.detail.ai.AIResponse
import com.example.standardcloneui.presentation.detail.ai.AIResultUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: AiRepository = AiRepository()) : ViewModel() {
    private val _uiState = MutableStateFlow<AIResultUIState>(AIResultUIState.Loading)
    val uiState: StateFlow<AIResultUIState> = _uiState

    fun fetchAiAnalysisResult(detail: ListItem.VideoItem) {
        viewModelScope.launch {
            _uiState.value = AIResultUIState.Loading
            try {
                val response =
                    repository.createChatCompletion(detail.createAiRequest())
                val aiResponse =
                    AIResponse(response.choices?.get(0)?.message?.content ?: "분석 결과 없음")
                _uiState.value = AIResultUIState.Success(aiResponse)
            } catch (e: Exception) {
                _uiState.value = AIResultUIState.Error(e.message ?: "AI 분석 실패")
            }
        }
    }
}

private fun ListItem.VideoItem.createAiRequest(): OpenAIRequest {
    return OpenAIRequest(
        messages = listOf(
            Message(
                "user",
                "제목: $title, 채널: $channelTitle, 설명: $description"
            ),
            Message(
                "system",
                "너는 단호하고 재미있는 유투브 컨텐츠 분석가야. " +
                        "유투브 비디오 title과 채널명, 설명을 분석해서 재미있을 것 같은지 " +
                        "추천여부 멘트를 재미있게 작성해줘."
            )
        )
    )
}
