package com.example.data.remote

import com.example.data.request.ChatBotDTO
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.request.PickNewsData
import com.example.data.request.RealTimeNewsDataDTO
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.InterestingKeyWordsResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.ScrapListResponse
import com.example.data.response.ScrapResponse
import com.example.data.response.SearchResponse
import com.example.data.response.SignInResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MainApi {

    // FCM 토큰 조회 API
    @POST("/api/auth/social-login")
    suspend fun postSocialLogIn(
        @Body socialLoginDTO: SocialLoginDTO
    ): BaseResponse<String>

    // 회원 가입
    @POST("/api/signin")
    suspend fun postSignIn(
        @Body signInDTO: SignInDTO
    ): BaseResponse<SignInResponse>

}