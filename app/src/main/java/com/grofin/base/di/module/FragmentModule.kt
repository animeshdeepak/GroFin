package com.grofin.base.di.module

import androidx.fragment.app.Fragment
import com.grofin.base.di.qualifire.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {
    @ContributesAndroidInjector
    @PerFragment
    abstract fun someFragment(): Fragment
}