package com.grofin.base.networking

import com.grofin.base.SharedPrefHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(private val sharedPrefHelper: SharedPrefHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Token", "Bearer " + sharedPrefHelper.getToken().orEmpty())
            .build()
        return chain.proceed(newRequest)
    }
}