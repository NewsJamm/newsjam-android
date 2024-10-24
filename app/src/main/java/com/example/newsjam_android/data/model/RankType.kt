package com.example.newsjam_android.data.model

sealed class RankType {
    data class HopTopicType(val rank : String, val topic : String) : RankType()
    data class SearchResultType(val publish: String, val profile: String = "data:image/jpeg;base64,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", val title : String, val arriveTime : String) : RankType()
}