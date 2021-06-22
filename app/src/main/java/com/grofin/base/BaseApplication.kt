package com.grofin.base

import com.grofin.base.di.AppComponent
import com.grofin.base.di.DaggerAppComponent
import com.grofin.base.di.DaggerBaseApplication
import com.grofin.base.di.module.NetworkModule
import com.grofin.base.networking.AppEndPoints

class BaseApplication : DaggerBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override val appComponent: AppComponent
        get() = DaggerAppComponent.builder()
            .application(this)
            .network(NetworkModule(AppEndPoints.BASE_URL))
            .build()
}