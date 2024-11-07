package com.example.data.response

data class ScrapListResponse(
    val scrapList : List<ScrapItem>
)

data class ScrapItem(
    val title : String,
    val pubData : String,
    val url : String
)