package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.ChatBotDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.ScrapResponse
import com.example.data.response.SearchResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostScrapUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(scrapDTO: NewsViewCountDTO
    ): Flow<BaseResponse<ScrapResponse>> = homeRepository.postScrap(scrapDTO)
}
