package com.example.domain.mapper

import com.example.data.model.ChatMessage
import com.example.data.model.NewsItem
import com.example.data.model.NewsItem2
import com.example.data.model.NewsItem3
import com.example.data.response.HotTopicResponse
import com.example.data.model.RankType
import com.example.data.request.PickNews
import com.example.data.request.PickNewsData
import com.example.data.request.RealTimeNewsDataDTO
import com.example.data.response.ChatBotResponse
import com.example.data.response.WordCloudClickInKeyWordResponse
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun HotTopicResponse.asDomain(): List<RankType.HopTopicType> {
    return this.word_list.map { item ->
        RankType.HopTopicType(
            rank = item.count,
            topic = item.word
        )
    }
}

fun RealTimeNewsDataDTO.asNewsItem2(): List<NewsItem2> {
    return this.pickNewsDataList.map{ item ->
        NewsItem2(
            tag = item.keyword,
            newsItem = item.pickNews.asNewsItem()
        )
    }
}


fun PickNewsData.asNewsItem3() : List<NewsItem3>{
    return this.recommendNews.map { item ->
        NewsItem3(
            description = item.content,
            newsItem = item.asNewsItem()
        )
    }
}

fun PickNews.asNewsItem() : NewsItem{
    return NewsItem(
        id = this.id ,
        title = this.title,
        arriveTime = formatRelativeTime(this.publish_date),
        url = this.url,
        content = this.content,
        profile = this.image_url
    )
}

fun WordCloudClickInKeyWordResponse.asNewsItem() : List<NewsItem>{
    return this.newsList.map { item ->
        item.asNewsItem()
    }
}

fun ChatBotResponse.toAI() : ChatMessage.AIMessage{
    return ChatMessage.AIMessage(
        message = this.chat_answer
    )
}

fun ChatBotResponse.toUser() : ChatMessage.HumanMessage{
    return ChatMessage.HumanMessage(
        message = this.chat_answer
    )
}

fun ChatBotResponse.toApp() : ChatMessage.AppMessage{
    return ChatMessage.AppMessage(
        message = this.chat_answer
    )
}

fun formatRelativeTime(dateTimeString: String): String {
    // 날짜 문자열을 LocalDateTime으로 변환
    val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH)
    val givenDateTime = LocalDateTime.parse(dateTimeString, formatter)
    val now = LocalDateTime.now(ZoneOffset.UTC)

    // 두 시간의 차이 계산
    val duration = Duration.between(givenDateTime, now)

    return when {
        duration.toMinutes() < 60 -> "${duration.toMinutes()}분 전"
        duration.toHours() < 24 -> "${duration.toHours()}시간 전"
        duration.toDays() < 30 -> "${duration.toDays()}일 전"
        duration.toDays() / 30 < 12 -> "${duration.toDays() / 30}달 전"
        else -> "${duration.toDays() / 365}년 전"
    }
}