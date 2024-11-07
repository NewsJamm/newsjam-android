package com.example.newsjam_android.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.NewsItem2
import com.example.data.model.NewsItem3
import com.example.data.model.RankType
import com.example.data.request.ChatBotDTO
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.InterestingKeyWordsResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import com.example.domain.mapper.asDomain
import com.example.domain.mapper.asNewsItem
import com.example.domain.mapper.asNewsItem2
import com.example.domain.mapper.asNewsItem3
import com.example.domain.usecase.GetHotTopicKeyWordUseCase
import com.example.domain.usecase.GetNewsPickUseCase
import com.example.domain.usecase.PostChatBotUseCase
import com.example.domain.usecase.PostInterestingKeyWordsUseCase
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
   private val postChatBotUseCase: PostChatBotUseCase
) : ViewModel() {

    private val _chat =
        MutableStateFlow(BaseResponse<ChatBotResponse>())
    val chat: StateFlow<BaseResponse<ChatBotResponse>> = _chat

    // 로그인 성공 상태 추가
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful

    fun postChatBot(chatBotDTO: ChatBotDTO) {
        viewModelScope.launch {
            try {
                postChatBotUseCase(chatBotDTO).collect { response ->
                    _chat.value = response
                }
            } catch (e: Exception) {
                Log.e("에러", e.toString())
            }
        }
    }


}