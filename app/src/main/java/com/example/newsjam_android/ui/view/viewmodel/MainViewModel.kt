package com.example.newsjam_android.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.RankType
import com.example.data.request.SignInDTO
import com.example.data.response.BaseResponse
import com.example.data.response.SignInData
import com.example.domain.mapper.asDomain
import com.example.domain.usecase.SearchUseCase
import com.example.domain.usecase.SocialLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _search = MutableStateFlow(BaseResponse<List<RankType.HopTopicType>>())
    val search: StateFlow<BaseResponse<List<RankType.HopTopicType>>> = _search

    // 로그인 성공 상태 추가
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful

    fun getHotTopic(count: String) {
        viewModelScope.launch {
            try {
                searchUseCase(count).collect { response ->
                    response.result?.let { result ->
                        _search.value = BaseResponse(
                            result = result
                                .asDomain()
                                .sortedBy { it.rank }
                                .mapIndexed { index, item -> item.copy(rank = "${index + 1}") }
                        )
                    }
                    _isLoginSuccessful.value = true
                }
            } catch (e: Exception) {
                Log.e("에러", e.toString())
                _isLoginSuccessful.value = false
            }
        }
    }

}