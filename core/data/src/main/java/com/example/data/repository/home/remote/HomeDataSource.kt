package com.example.data.repository.home.remote

import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {
    suspend fun getHotTopic(count : String) : Flow<BaseResponse<HotTopicResponse>>
}