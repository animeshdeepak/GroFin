package com.grofin.base.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.grofin.base.di.module.ActivityModule
import com.grofin.base.di.module.NetworkModule
import com.grofin.base.di.module.RepoModule
import com.grofin.base.di.module.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, RepoModule::class, ViewModelModule::class, ActivityModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences("GroMo", Context.MODE_PRIVATE)
}