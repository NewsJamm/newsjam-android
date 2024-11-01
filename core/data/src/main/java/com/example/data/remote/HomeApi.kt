package com.example.data.remote

import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.SignInData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeApi {

    // 핫 토픽 데이터 요청
    @GET("/api/news/hot-topic")
    suspend fun getHotTopic(
        @Query("count") count : String
    ): BaseResponse<HotTopicResponse>



}