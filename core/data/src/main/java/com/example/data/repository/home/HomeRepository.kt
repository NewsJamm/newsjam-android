package com.example.data.repository.home

import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHotTopic(count: String) : Flow<BaseResponse<HotTopicResponse>>
}