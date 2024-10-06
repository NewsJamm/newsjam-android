package com.example.newsjam_android.ui.base

data class BaseResponse<T>(
    val message : String = "",
    val result: T? = null
)
