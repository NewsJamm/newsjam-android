package com.example.data.response

data class HotTopicResponse(
    val word_list : List<Word> = emptyList()
)

data class Word(
    val word : String = "",
    val count : String = "0"
)
