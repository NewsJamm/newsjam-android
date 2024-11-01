package com.example.data.remote

import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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
    ): SignInData

}