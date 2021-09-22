package com.grofin.base.networking

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequset = originalRequest.newBuilder()
            .addHeader("key_header_1", "value_header_1")
            .build()
        return chain.proceed(newRequset)
    }
}