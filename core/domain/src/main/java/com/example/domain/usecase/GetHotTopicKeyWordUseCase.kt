package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.ChatBotDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.SearchResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHotTopicKeyWordUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(
        keyword: String,
        page: String,
        pageSize: String
    ): Flow<BaseResponse<WordCloudClickInKeyWordResponse>> = homeRepository.getHotTopicKeyWord(keyword,page,pageSize)
}
