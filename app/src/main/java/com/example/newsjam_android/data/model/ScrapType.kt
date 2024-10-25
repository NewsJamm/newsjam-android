package com.example.newsjam_android.data.model

data class ScrapType(
    val searchResultType: RankType.SearchResultType = RankType.SearchResultType(
        publish = "스브스뉴스",
        title = "배고프다 나!!",
        arriveTime = "4분전"
    ),
    val isScrap : Boolean = false
)
