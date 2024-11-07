package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.ChatBotDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.request.RealTimeNewsDataDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.ScrapListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScrapListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
    ): Flow<BaseResponse<ScrapListResponse>> = homeRepository.getScrapList()
}
