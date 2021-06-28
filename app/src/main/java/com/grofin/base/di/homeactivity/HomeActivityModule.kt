package com.grofin.base.di.homeactivity

import com.grofin.dashboard.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [HomeActivitySubComponent::class])
abstract class HomeActivityModule {
    @Binds
    @IntoMap
    @ClassKey(HomeActivity::class)
    abstract fun bindYourAndroidInjectorFactory(factory: HomeActivitySubComponent.Factory): AndroidInjector.Factory<Any>
}