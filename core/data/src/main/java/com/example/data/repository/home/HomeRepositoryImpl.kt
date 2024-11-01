package com.example.data.repository.home

import com.example.data.repository.account.remote.AccountDataSource
import com.example.data.repository.home.remote.HomeDataSource
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.SignInData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHotTopic(count: String): Flow<BaseResponse<HotTopicResponse>> = homeDataSource.getHotTopic(count)

}