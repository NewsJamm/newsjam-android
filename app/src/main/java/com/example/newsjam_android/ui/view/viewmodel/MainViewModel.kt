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
import com.example.domain.usecase.GetHotTopicKeyWordUseCase
import com.example.domain.usecase.GetNewsPickUseCase
import com.example.domain.usecase.PostInterestingKeyWordsUseCase
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.security.PrivateKey
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val interestingKeyWordsUseCase: PostInterestingKeyWordsUseCase,
    private val getNewsPickUseCase: GetNewsPickUseCase,
    private val getHotTopicKeyWordUseCase: GetHotTopicKeyWordUseCase
) : ViewModel() {
    private val _search = MutableStateFlow(BaseResponse<List<RankType.HopTopicType>>())
    val search: StateFlow<BaseResponse<List<RankType.HopTopicType>>> = _search

    private val _hotTopic = MutableStateFlow(BaseResponse<HotTopicResponse>())
    val hotTopic: StateFlow<BaseResponse<HotTopicResponse>> = _hotTopic

    private val _interestingKeyWord = MutableStateFlow(InterestingKeyWordsResponse())
    val interestingKeyWord: StateFlow<InterestingKeyWordsResponse> = _interestingKeyWord

    private val _newsPick =
        MutableStateFlow(BaseResponse<Pair<List<List<NewsItem3>>, List<NewsItem2>>>())
    val newsPick: StateFlow<BaseResponse<Pair<List<List<NewsItem3>>, List<NewsItem2>>>> = _newsPick

    private val _keyWord =
        MutableStateFlow(BaseResponse<WordCloudClickInKeyWordResponse>())
    val keyWord: StateFlow<BaseResponse<WordCloudClickInKeyWordResponse>> = _keyWord

    // 로그인 성공 상태 추가
    private val _isLoginSuccessful = MutableStateFlow(false)
    val isLoginSuccessful: StateFlow<Boolean> = _isLoginSuccessful

    fun getHotTopicSearch(count: String) {
        viewModelScope.launch {
            try {
                searchUseCase(count).collect { response ->
                    response.result?.let { result ->
                        _search.value = BaseResponse(
                            result = result
                                .asDomain()
                                .sortedBy { it.rank }
                                .mapIndexed { index, item -> item.copy(rank = "${index + 1}") },
                            message = response.message
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

    fun getHotTopic(count: String) {
        viewModelScope.launch {
            try {
                searchUseCase(count).collect { response ->
                    _hotTopic.value = response
                    _isLoginSuccessful.value = true
                }
            } catch (e: Exception) {
                Log.e("에러", e.toString())
                _isLoginSuccessful.value = false
            }
        }
    }

    fun postInterestingKeyWords(interestingKeyWordsDTO: InterestingKeyWordsDTO) {
        viewModelScope.launch {
            try {
                interestingKeyWordsUseCase(interestingKeyWordsDTO).collect {
                    _interestingKeyWord.value = it
                    Log.e("에러", "$_interestingKeyWord")
                }
            } catch (e: Exception) {
                Log.e("에러", e.toString())
            }
        }
    }

    //List<List<NewsItem3>>  List<NewsItem2>
    fun getRealTimeNewsPick(page: String, pageSize: String) {
        viewModelScope.launch {
            try {
                getNewsPickUseCase(page, pageSize).collect { response ->
                    response.result?.let { result ->
                        val result1 = result.pickNewsDataList.map { it.asNewsItem3() }
                        val result2 = result.asNewsItem2()
                        _newsPick.value = BaseResponse(
                            result = Pair(result1, result2),
                            message = response.message
                        )
                        Log.e("NewsPick Result", "$_newsPick")
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

    fun getKeyHotTopicKeyWord(keyword:String, page: String, pageSize: String) {
        viewModelScope.launch {
            try {
                getHotTopicKeyWordUseCase(keyword, page, pageSize).collect { response ->
                    response.result?.let { result ->
                        result.newsList.map { it.asNewsItem() }
                        _keyWord.value = response
                        Log.e("NewsPick Result", "$_newsPick")
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.toString())
            }
        }
    }

}