package com.example.domain.mapper

import com.example.data.response.HotTopicResponse
import com.example.data.model.RankType

fun HotTopicResponse.asDomain(): List<RankType.HopTopicType> {
    return this.word_list.map { item ->
        RankType.HopTopicType(
            rank = item.count,
            topic = item.word
        )
    }
}
