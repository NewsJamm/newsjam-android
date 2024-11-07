package com.example.data.request

data class RealTimeNewsDataDTO(
    val pickNewsDataList : List<PickNewsData> = mutableListOf(),
    val listSize : Int = 0,
    val totalPage : Int = 0,
    val totalElements : Int = 0,
    val isFirst : Boolean = false,
    val isLast : Boolean = false
)

data class PickNewsData(
    val keyword : String = "",
    val pickNews : PickNews = PickNews(),
    val recommendNews : List<PickNews> = mutableListOf()
)

data class PickNews(
    val id : String = "",
    val title : String = "",
    val content : String = "",
    val publish_date : String = "",
    val url : String = "",
    val image_url : String = ""
)
