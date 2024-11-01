package com.example.data.response

data class BaseResponse<T>(
    val loadState: LoadState = LoadState.LOADING,
    val isSuccess: Boolean = false,
    val code: String = "",
    val message: String = "",
    var result: T? = null
)
