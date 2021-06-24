package com.grofin.base.di.module

import com.grofin.SplashActivity
import com.grofin.base.di.qualifire.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun splashActivity(): SplashActivity
}