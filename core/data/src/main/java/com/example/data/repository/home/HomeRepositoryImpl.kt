package com.example.data.repository.home

import com.example.data.repository.account.remote.AccountDataSource
import com.example.data.repository.home.remote.HomeDataSource
import com.example.data.request.ChatBotDTO
import com.example.data.request.InterestingKeyWordsDTO
import com.example.data.request.NewsViewCountDTO
import com.example.data.request.RealTimeNewsDataDTO
import com.example.data.request.SignInDTO
import com.example.data.request.SocialLoginDTO
import com.example.data.response.BaseResponse
import com.example.data.response.ChatBotResponse
import com.example.data.response.HotTopicResponse
import com.example.data.response.InterestingKeyWordsResponse
import com.example.data.response.NewsViewCountResponse
import com.example.data.response.ScrapListResponse
import com.example.data.response.ScrapResponse
import com.example.data.response.SearchResponse
import com.example.data.response.SignInData
import com.example.data.response.WordCloudClickInKeyWordResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getHotTopic(count: String
    ): Flow<BaseResponse<HotTopicResponse>> = homeDataSource.getHotTopic(count)

    override suspend fun postIncreaseViewCnt(newsViewCountDTO: NewsViewCountDTO
    ): Flow<BaseResponse<NewsViewCountResponse>> = homeDataSource.postIncreaseViewCnt(newsViewCountDTO)

    override suspend fun postInterestingKeyWords(interestingKeyWordsDTO: InterestingKeyWordsDTO
    ): Flow<InterestingKeyWordsResponse> = homeDataSource.postInterestingKeyWords(interestingKeyWordsDTO)

    override suspend fun deleteMember(
    ): Flow<BaseResponse<String>> = homeDataSource.deleteMember()

    override suspend fun postChatBot(chatBotDTO: ChatBotDTO
    ): Flow<BaseResponse<ChatBotResponse>> = homeDataSource.postChatBot(chatBotDTO)

    override suspend fun getSearch(query: String
    ): Flow<BaseResponse<SearchResponse>> = homeDataSource.getSearch(query)

    override suspend fun getNewsPick(
        page: String,
        pageSize: String
    ): Flow<BaseResponse<RealTimeNewsDataDTO>> = homeDataSource.getNewsPick(page,pageSize)

    override suspend fun getHotTopicKeyWord(
        keyword: String,
        page: String,
        pageSize: String
    ): Flow<BaseResponse<WordCloudClickInKeyWordResponse>> = homeDataSource.getHotTopicKeyWord(keyword,page,pageSize)

    override suspend fun getCategory(
        category: String,
        sortStatus: String,
        page: String,
        pageSize: String
    ): Flow<BaseResponse<WordCloudClickInKeyWordResponse>> = homeDataSource.getCategory(category,sortStatus,page,pageSize)

    override suspend fun postScrap(scrapDTO: NewsViewCountDTO
    ): Flow<BaseResponse<ScrapResponse>> = homeDataSource.postScrap(scrapDTO)

    override suspend fun deleteScrap(scrapDTO: NewsViewCountDTO
    ): Flow<BaseResponse<ScrapResponse>> = homeDataSource.deleteScrap(scrapDTO)

    override suspend fun getScrapList(
    ): Flow<BaseResponse<ScrapListResponse>> = homeDataSource.getScrapList()

}