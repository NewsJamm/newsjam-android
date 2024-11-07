package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.ChatBotDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(query: String
    ): Flow<BaseResponse<SearchResponse>> = homeRepository.getSearch(query)
}
