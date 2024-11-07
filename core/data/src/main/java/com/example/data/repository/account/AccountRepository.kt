package com.example.data.repository.account

import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import com.example.data.response.SignInResponse
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun postSocialLogIn(socialLoginDTO: SocialLoginDTO) : Flow<BaseResponse<String>>
    suspend fun postSignIn(signInDTO: SignInDTO) : Flow<BaseResponse<SignInResponse>>
}