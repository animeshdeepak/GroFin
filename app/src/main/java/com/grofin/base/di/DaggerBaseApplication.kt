package com.grofin.base.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

abstract class DaggerBaseApplication : DaggerApplication() {
    abstract val appComponent: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}