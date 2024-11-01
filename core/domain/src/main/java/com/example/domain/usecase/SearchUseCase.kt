package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(count: String): Flow<BaseResponse<HotTopicResponse>> {
        return homeRepository.getHotTopic(count)
    }

}