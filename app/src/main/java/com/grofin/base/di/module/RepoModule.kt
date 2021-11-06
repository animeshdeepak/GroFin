package com.grofin.base.di.module

import com.grofin.base.repo.*
import com.grofin.base.service.HomeService
import com.grofin.base.service.LoginRegisterService
import com.grofin.base.service.SplashService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun provideSplashRepo(service: SplashService): SplashRepo = SplashRepoImpl(service)

    @Singleton
    @Provides
    fun provideLoginRegisterRepo(service: LoginRegisterService): LoginRegisterRepo = LoginRegisterRepoImpl(service)

    @Singleton
    @Provides
    fun provideHomeRepo(service: HomeService): HomeRepo = HomeRepoImpl(service)
}