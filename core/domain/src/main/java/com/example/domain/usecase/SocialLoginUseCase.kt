package com.example.domain.usecase

import com.example.data.repository.account.AccountRepository
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SocialLoginUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(socialLoginDTO: SocialLoginDTO): Flow<BaseResponse<String>> {
        return accountRepository.postSocialLogIn(socialLoginDTO)
    }

    suspend operator fun invoke(signInDTO: SignInDTO) : Flow<SignInData> {
        return accountRepository.postSignIn(signInDTO)
    }
}