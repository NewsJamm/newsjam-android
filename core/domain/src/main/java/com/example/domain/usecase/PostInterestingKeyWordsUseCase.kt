package com.example.domain.usecase

import com.example.data.repository.home.HomeRepository
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.response.BaseResponse
import com.example.data.response.InterestingKeyWordsResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostInterestingKeyWordsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(interestingKeyWordsDTO: InterestingKeyWordsDTO
    ): Flow<InterestingKeyWordsResponse> = homeRepository.postInterestingKeyWords(interestingKeyWordsDTO)
}
