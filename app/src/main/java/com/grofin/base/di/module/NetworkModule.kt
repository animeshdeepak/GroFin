package com.grofin.base.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grofin.base.SharedPrefHelper
import com.grofin.base.networking.*
import com.grofin.base.networking.qualifier.AuthUrlAPI
import com.grofin.base.service.LoginRegisterService
import com.grofin.base.service.SplashService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {
    companion object {
        private const val READ_TIME_OUT_SECONDS = 400L
        private const val CONNECT_TIME_OUT_SECONDS = 400L
        private const val WRITE_TIME_OUT_SECONDS = 400L
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(context: Context): ArrayList<Interceptor> {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }
        return arrayListOf<Interceptor>().apply {
            add(logging)
            add(ConnectivityInterceptor(context))
        }
    }

    @Singleton
    @Provides
    fun provideGson(
        serializationStrategy: SerializationStrategy,
        deserializationStrategy: DeserializationStrategy
    ): Gson {
        return GsonBuilder()
            .setLenient()
            .addSerializationExclusionStrategy(serializationStrategy)
            .addDeserializationExclusionStrategy(deserializationStrategy)
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(commonInterceptor: ArrayList<Interceptor>): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS)
        }
//        add common interceptor
        if (commonInterceptor.isNotEmpty())
            commonInterceptor.forEach {
                okHttpClientBuilder.addInterceptor(it)
            }

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @AuthUrlAPI
    fun provideRetrofitInstanceWithAuthToken(
        context: Context,
        serializationStrategy: SerializationStrategy,
        deserializationStrategy: DeserializationStrategy,
        sharedPrefHelper: SharedPrefHelper
    ): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }

        val commonInterceptor = arrayListOf<Interceptor>().apply {
            add(HeaderInterceptor(sharedPrefHelper))
            add(logging)
            add(ConnectivityInterceptor(context))
        }

        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS)
        }
//        add common interceptor
        if (commonInterceptor.isNotEmpty())
            commonInterceptor.forEach {
                okHttpClientBuilder.addInterceptor(it)
            }

        val gson = GsonBuilder()
            .setLenient()
            .addSerializationExclusionStrategy(serializationStrategy)
            .addDeserializationExclusionStrategy(deserializationStrategy)
            .create()

        return Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSplashService(retrofit: Retrofit): SplashService =
        SplashService(retrofit.create(AppApis::class.java))

    @Singleton
    @Provides
    fun provideLoginRegisterService(retrofit: Retrofit, @AuthUrlAPI authRetrofit: Retrofit): LoginRegisterService =
        LoginRegisterService(retrofit.create(AppApis::class.java), authRetrofit.create(AppApis::class.java))
}