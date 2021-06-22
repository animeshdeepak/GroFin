package com.grofin.base

import com.grofin.base.di.AppComponent
import com.grofin.base.di.DaggerBaseApplication

class BaseApplication : DaggerBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
    override val appComponent: AppComponent
        get() =
}