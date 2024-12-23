package com.namchok.forecast.data.interceptor

import com.namchok.forecast.Secrets
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticatorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url =
            request.url.newBuilder().apply {
                addQueryParameter("appid", Secrets().apiKey)
            }.build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}
