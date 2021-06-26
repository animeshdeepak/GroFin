package com.grofin.base.di.module

import com.grofin.base.di.qualifire.ActivityScope
import com.grofin.dashboard.HomeActivity
import com.grofin.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun homeActivity(): HomeActivity
}