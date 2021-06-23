package com.grofin.base.di.module

import com.grofin.SplashFragment
import com.grofin.base.di.qualifire.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {
    @ContributesAndroidInjector
    @PerFragment
    abstract fun splashFragment(): SplashFragment
}