package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.ChatBotDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.NewsViewCountResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostChatBotUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(chatBotDTO: ChatBotDTO
    ): Flow<BaseResponse<ChatBotResponse>> = homeRepository.postChatBot(chatBotDTO)
}
