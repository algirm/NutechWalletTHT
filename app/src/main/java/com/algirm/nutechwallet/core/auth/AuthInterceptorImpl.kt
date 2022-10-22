package com.algirm.nutechwallet.core.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class AuthInterceptorImpl @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var userToken = sessionManager.getUserToken()

        val response = chain.proceed(newRequestWithAccessToken(userToken, request))

        if (response.code == STATUS_TOKEN_ERROR) {
            val newUserToken = sessionManager.getUserToken()
            if (newUserToken != userToken) {
                return chain.proceed(newRequestWithAccessToken(userToken, request))
            } else {
                userToken = refreshToken()
                if (userToken.isBlank()) {
                    sessionManager.logout()
                    return response
                }
                return chain.proceed(newRequestWithAccessToken(userToken, request))
            }
        }

        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()

    private fun refreshToken(): String {
        synchronized(this) {
            return sessionManager.getUserToken()
        }
    }

    companion object {
        const val STATUS_TOKEN_ERROR = 108
    }
}