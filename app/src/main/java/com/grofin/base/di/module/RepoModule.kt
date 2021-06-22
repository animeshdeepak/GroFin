package com.grofin.base.di.module

import com.grofin.base.repo.SplashRepo
import com.grofin.base.repo.SplashRepoImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun provideSplashRepo(): SplashRepo = SplashRepoImpl()
}