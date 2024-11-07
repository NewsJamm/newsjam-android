package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.NewsViewCountDTO
import com.example.data.response.BaseResponse
import com.example.data.response.NewsViewCountResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMemberUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(newsViewCountDTO: NewsViewCountDTO): Flow<BaseResponse<NewsViewCountResponse>> =
        homeRepository.postIncreaseViewCnt(newsViewCountDTO)
}
