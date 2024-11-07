package com.example.newsjam_android.ui.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.NewsItem2
import com.example.data.model.NewsItem3
import com.example.data.model.RankType
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.response.BaseResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.InterestingKeyWordsResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import com.example.domain.mapper.asDomain
import com.example.domain.mapper.asNewsItem
import com.example.domain.mapper.asNewsItem2
import com.example.domain.mapper.asNewsItem3
import com.example.domain.usecase.GetCategoryUseCase
import com.example.domain.usecase.GetHotTopicKeyWordUseCase
import com.example.domain.usecase.GetNewsPickUseCase
import com.example.domain.usecase.PostInterestingKeyWordsUseCase
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
   private val categoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _category =
        MutableStateFlow(BaseResponse<WordCloudClickInKeyWordResponse>())
    val category: StateFlow<BaseResponse<WordCloudClickInKeyWordResponse>> = _category

    // 로그인 성공 상태 추가

    fun getCategory(category :String, sortStatus : String, page: String, pageSize : String) {
        viewModelScope.launch {
            try {
                categoryUseCase(category, sortStatus, page, pageSize).collect { response ->
                        _category.value = response
                        Log.e("NewsPick Result", "$_category")
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

}