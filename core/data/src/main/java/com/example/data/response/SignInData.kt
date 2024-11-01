package com.example.data.response

data class SignInData(
    val id: Int = 0,
    val loginId: String = "",
    val loginPw: String = "",
    val memberName: String = "",
    val authProvider: String = "LOCAL",
    val providerId: String = "",
    val scrapList: List<Scrap> = emptyList(),
    val authorities: List<Authority> = emptyList(),
    val interestingKeywords: List<String> = emptyList(),
    val refreshToken: String = "",
    val role: Role = Role()
)

data class Scrap(
    val id: Int = 0,
    val newsUrl: String = "",
    val member: String = ""
)

data class Authority(
    val id: Int = 0,
    val type: String = "ROLE_MEMBER",
    val member: String = ""
)

data class Role(
    val id: Int = 0,
    val type: String = "ROLE_MEMBER",
    val member: String = ""
)
