package com.example.data.extensions

import android.util.Log
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ResponseInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization" // 서버에서 보내는 헤더 이름
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        // 서버 응답 헤더에서 토큰을 추출
        val token = response.header(HEADER_AUTHORIZATION)
        Log.d("헤더 토큰", token.toString())

        // 토큰이 null이 아닌 경우 TokenManager에 저장
        runBlocking {
            if(token.isNullOrBlank())
                tokenManager.saveAccessToken(token.toString())
            else{
                tokenManager.saveAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBY2Nlc3NUb2tlbiIsImlhdCI6MTczMDQ2MDIyMiwiZXhwIjoxNzMxMzI0MjIyLCJwcm92aWRlcl90eXBlIjoiR09PR0xFIiwicHJvdmlkZXJfaWQiOiIxMTM3MDM0MTY3NTUyNTk0MDI1OTQifQ.QGUcY6Mle7fNgkJUuKPYBFS8d_zPZqYaIm4kc2p0WcQ")
            }
        }

        return response
    }
}
