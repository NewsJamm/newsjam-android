package com.example.data.repository.home.remote

import android.util.Log
import com.example.data.remote.HomeApi
import com.example.data.request.ChatBotDTO
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.request.RealTimeNewsDataDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.InterestingKeyWordsResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.ScrapListResponse
import com.example.data.response.ScrapResponse
import com.example.data.response.SearchResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeDataSource {
    override suspend fun getHotTopic(count: String): Flow<BaseResponse<HotTopicResponse>> = flow {
        val result = homeApi.getHotTopic(count)
        emit(result)
    }.catch { e ->
        Log.e("getHotTopic 에러", e.message.toString())
    }

    override suspend fun postIncreaseViewCnt(newsViewCountDTO: NewsViewCountDTO): Flow<BaseResponse<NewsViewCountResponse>>  = flow {
        val result = homeApi.postIncreaseViewCnt(newsViewCountDTO)
        emit(result)
    }.catch { e ->
        Log.e("postIncreaseViewCnt 에러", e.message.toString())
    }

    override suspend fun postInterestingKeyWords(interestingKeyWordsDTO: InterestingKeyWordsDTO): Flow<InterestingKeyWordsResponse>  = flow {
        val result = homeApi.postInterestingKeyWords(interestingKeyWordsDTO)
        emit(result)
    }.catch { e ->
        Log.e("postInterestingKeyWords 에러", e.message.toString())
    }

    override suspend fun deleteMember(): Flow<BaseResponse<String>>  = flow {
        val result = homeApi.deleteMember()
        emit(result)
    }.catch { e ->
        Log.e("deleteMember 에러", e.message.toString())
    }

    override suspend fun postChatBot(chatBotDTO: ChatBotDTO): Flow<BaseResponse<ChatBotResponse>> = flow {
        val result = homeApi.postChatBot(chatBotDTO)
        emit(result)
    }.catch { e ->
        Log.e("postChatBot 에러", e.message.toString())
    }

    override suspend fun getSearch(query: String): Flow<BaseResponse<SearchResponse>> = flow {
        val result = homeApi.getSearch(query)
        emit(result)
    }.catch { e ->
        Log.e("getSearch 에러", e.message.toString())
    }

    override suspend fun getNewsPick(
        page: String,
        pageSize: String
    ): Flow<BaseResponse<RealTimeNewsDataDTO>> = flow {
        val result = homeApi.getNewsPick(page,pageSize)
        emit(result)
    }.catch { e ->
        Log.e("getNewsPick 에러", e.message.toString())
    }

    override suspend fun getHotTopicKeyWord(
        keyword: String,
        page: String,
        pageSize: String
    ): Flow<BaseResponse<WordCloudClickInKeyWordResponse>> = flow {
        val result = homeApi.getHotTopicKeyWord(keyword,page,pageSize)
        emit(result)
    }.catch { e ->
        Log.e("getHotTopicKeyWord 에러", e.message.toString())
    }

    override suspend fun getCategory(
        category: String,
        sortStatus: String,
        page: String,
        pageSize: String
    ): Flow<BaseResponse<WordCloudClickInKeyWordResponse>>  = flow {
        val result = homeApi.getCategory(category,sortStatus,page,pageSize)
        emit(result)
    }.catch { e ->
        Log.e("getCategory 에러", e.message.toString())
    }

    override suspend fun postScrap(scrapDTO: NewsViewCountDTO): Flow<BaseResponse<ScrapResponse>>  = flow {
        val result = homeApi.postScrap(scrapDTO)
        emit(result)
    }.catch { e ->
        Log.e("scrapDTO 에러", e.message.toString())
    }

    override suspend fun deleteScrap(scrapDTO: NewsViewCountDTO): Flow<BaseResponse<ScrapResponse>> = flow {
        val result = homeApi.deleteScrap(scrapDTO)
        emit(result)
    }.catch { e ->
        Log.e("deleteScrap 에러", e.message.toString())
    }

    override suspend fun getScrapList(): Flow<BaseResponse<ScrapListResponse>> = flow {
        val result = homeApi.getScrapList()
        emit(result)
    }.catch { e ->
        Log.e("getScrapList 에러", e.message.toString())
    }

}