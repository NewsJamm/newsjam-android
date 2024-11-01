package com.example.data.repository.account

import com.example.data.repository.account.remote.AccountDataSource
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountDataSource: AccountDataSource
) : AccountRepository {
    override suspend fun postSocialLogIn(socialLoginDTO: SocialLoginDTO): Flow<BaseResponse<String>>
    = accountDataSource.postSocialLogIn(socialLoginDTO)

    override suspend fun postSignIn(signInDTO: SignInDTO): Flow<SignInData>
    = accountDataSource.postSignIn(signInDTO)


}