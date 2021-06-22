package com.grofin.base.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grofin.base.networking.AppApis
import com.grofin.base.networking.AppEndPoints
import com.grofin.base.networking.DeserializationStrategy
import com.grofin.base.networking.SerializationStrategy
import com.grofin.base.service.SplashService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideRetrofitInstance(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppEndPoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSplashService(retrofit: Retrofit): SplashService =
        SplashService(retrofit.create(AppApis::class.java))
}