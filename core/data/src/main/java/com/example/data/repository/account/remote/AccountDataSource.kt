package com.example.data.repository.account.remote

import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import kotlinx.coroutines.flow.Flow

interface AccountDataSource{
    suspend fun postSocialLogIn(socialLoginDTO: SocialLoginDTO) : Flow<BaseResponse<String>>
    suspend fun postSignIn(signInDTO: SignInDTO) : Flow<SignInData>
}