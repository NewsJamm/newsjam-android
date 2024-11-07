package com.example.data.remote

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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeApi {

    // 핫 토픽 데이터 요청
    @GET("/api/news/hot-topic")
    suspend fun getHotTopic(
        @Query("count") count : String
    ): BaseResponse<HotTopicResponse>

    @POST("/increaseViewCnt")
    suspend fun postIncreaseViewCnt(
        @Body newsViewCountDTO: NewsViewCountDTO
    ): BaseResponse<NewsViewCountResponse>

    @POST("/api/interesting-keywords")
    suspend fun postInterestingKeyWords(
        @Body interestingKeyWordsDTO: InterestingKeyWordsDTO
    ): InterestingKeyWordsResponse

    @DELETE("/api/memberDelete")
    suspend fun deleteMember(): BaseResponse<String>

    @POST("/api/chatbot")
    suspend fun postChatBot(
        @Body chatBotDTO: ChatBotDTO
    ): BaseResponse<ChatBotResponse>

    @GET("/search")
    suspend fun getSearch(
        @Query("query") query: String
    ): BaseResponse<SearchResponse>

    @GET("/api/news/pick")
    suspend fun getNewsPick(
        @Query("page") page : String,
        @Query("pageSize") pageSize : String,
    ) : BaseResponse<RealTimeNewsDataDTO>

    @GET("/api/news/hot-topic/keyword")
    suspend fun getHotTopicKeyWord(
        @Query("keyword") keyword : String,
        @Query("page") page : String,
        @Query("pageSize") pageSize : String
    ) : BaseResponse<WordCloudClickInKeyWordResponse>

    @GET("api/news/category")
    suspend fun getCategory(
        @Query("category") category : String,
        @Query("sortStatus") sortStatus : String,
        @Query("page") page : String,
        @Query("pageSize") pageSize : String
    ) : BaseResponse<WordCloudClickInKeyWordResponse>

    @POST("/scrap")
    suspend fun postScrap(
        @Body scrapDTO : NewsViewCountDTO
    ) : BaseResponse<ScrapResponse>

    @DELETE("/scrapUndo")
    suspend fun deleteScrap(
        @Body scrapDTO : NewsViewCountDTO
    ) : BaseResponse<ScrapResponse>

    @GET("getScrapList")
    suspend fun getScrapList() : BaseResponse<ScrapListResponse>

}