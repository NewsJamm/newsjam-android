package com.example.data.request

data class InterestingKeywordRequest(
    val interestingKeywords: List<String>
)

data class Scrap(
    val id: Int,
    val newsUrl: String,
    val member: String
)

data class Authority(
    val id: Int,
    val type: String,
    val member: String
)

data class Role(
    val id: Int,
    val type: String,
    val member: String
)

data class Member(
    val id: Int,
    val loginId: String,
    val loginPw: String,
    val memberName: String,
    val authProvider: String,
    val providerId: String,
    val scrapList: List<Scrap>,
    val authorities: List<Authority>,
    val interestingKeywords: List<String>,
    val refreshToken: String,
    val role: Role
)

