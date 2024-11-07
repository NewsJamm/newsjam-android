package com.example.data.repository.account.remote

import android.util.Log
import com.example.data.remote.MainApi
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import com.example.data.response.SignInResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountDataSourceImpl @Inject constructor(
    private val mainApi: MainApi
) : AccountDataSource {

    override suspend fun postSocialLogIn(
        socialLoginDTO: SocialLoginDTO
    ): Flow<BaseResponse<String>> = flow {
        val result = mainApi.postSocialLogIn(socialLoginDTO)
        emit(result)
    }.catch { e ->
        Log.e("postSocialLogIn 에러", e.message.toString())
    }

    override suspend fun postSignIn(signInDTO: SignInDTO): Flow<BaseResponse<SignInResponse>> = flow {
        val result = mainApi.postSignIn(signInDTO)
        emit(result)
    }.catch { e ->
        Log.e("postSignIn 에러", e.message.toString())
    }

}