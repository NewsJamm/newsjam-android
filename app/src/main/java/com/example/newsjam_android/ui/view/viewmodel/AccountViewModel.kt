package com.example.newsjam_android.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.request.SignInDTO
import com.example.data.response.SignInResponse
import com.example.domain.usecase.SocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val loginUseCase: SocialLoginUseCase
) : ViewModel() {
    private val _signIn = MutableStateFlow(SignInResponse())
    val signIn: StateFlow<SignInResponse> = _signIn

    // 로그인 성공 상태 추가
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful

    fun postSignIn(signInDTO: SignInDTO) {
        viewModelScope.launch {
            try {
                loginUseCase(signInDTO).collect { signInResponse ->
                    _signIn.value = signInResponse.result!!
                    _isLoginSuccessful.value = true  // 로그인 성공 시 true로 설정
                }
            } catch (e: Exception) {
                Log.e("에러", e.toString())
                _isLoginSuccessful.value = false  // 로그인 실패 시 false로 설정
            }
        }
    }
}