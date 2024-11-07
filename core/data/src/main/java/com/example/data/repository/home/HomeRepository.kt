package com.example.data.repository.home

import com.example.data.request.ChatBotDTO
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.request.RealTimeNewsDataDTO
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

interface HomeRepository {
    suspend fun getHotTopic(count: String) : Flow<BaseResponse<HotTopicResponse>>
    suspend fun postIncreaseViewCnt(newsViewCountDTO: NewsViewCountDTO): Flow<BaseResponse<NewsViewCountResponse>>
    suspend fun postInterestingKeyWords(interestingKeyWordsDTO: InterestingKeyWordsDTO): Flow<InterestingKeyWordsResponse>
    suspend fun deleteMember(): Flow<BaseResponse<String>>
    suspend fun postChatBot(chatBotDTO: ChatBotDTO): Flow<BaseResponse<ChatBotResponse>>
    suspend fun getSearch(query: String): Flow<BaseResponse<SearchResponse>>
    suspend fun getNewsPick(page: String, pageSize: String): Flow<BaseResponse<RealTimeNewsDataDTO>>
    suspend fun getHotTopicKeyWord(keyword: String, page: String, pageSize: String): Flow<BaseResponse<WordCloudClickInKeyWordResponse>>
    suspend fun getCategory(category : String,sortStatus : String,page : String,pageSize : String):Flow<BaseResponse<WordCloudClickInKeyWordResponse>>
    suspend fun postScrap(scrapDTO : NewsViewCountDTO) : Flow<BaseResponse<ScrapResponse>>
    suspend fun deleteScrap(scrapDTO : NewsViewCountDTO) : Flow<BaseResponse<ScrapResponse>>
    suspend fun getScrapList() : Flow<BaseResponse<ScrapListResponse>>
}