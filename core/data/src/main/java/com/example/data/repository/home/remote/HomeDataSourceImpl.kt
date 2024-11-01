package com.example.data.repository.home.remote

import android.util.Log
import com.example.data.remote.HomeApi
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeDataSource {
    override suspend fun getHotTopic(count: String): Flow<BaseResponse<HotTopicResponse>> = flow {
        val result = homeApi.getHotTopic(count)
        emit(result)
    }.catch { e ->
        Log.e("getHotTopic 에러", e.message.toString())
    }

}