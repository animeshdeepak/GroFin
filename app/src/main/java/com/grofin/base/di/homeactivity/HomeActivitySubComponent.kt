package com.grofin.base.di.homeactivity

import com.grofin.dashboard.HomeActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [])
interface HomeActivitySubComponent : AndroidInjector<HomeActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<HomeActivity>
}