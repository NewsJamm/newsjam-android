package com.example.data.response

import com.example.data.request.PickNews

data class WordCloudClickInKeyWordResponse(
   val newsList : List<PickNews>,
    val listSize : Int,
    val totalPage : Int,
    val totalElements : Int,
    val isFirst : Boolean,
    val isLast : Boolean
)
