package com.example.data.response


data class SearchResponse(
    val foundNews : List<FoundNews>
)

data class FoundNews(
    val title: String,
    val pubDate: String,
    val url: String
)
