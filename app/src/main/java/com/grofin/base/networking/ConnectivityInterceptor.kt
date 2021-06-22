package com.grofin.base.networking

import android.content.Context
import com.grofin.base.exception.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!Connectivity.isNetworkConnected(context))
            throw NoConnectivityException()

        return chain.proceed(chain.request().newBuilder().build())
    }
}