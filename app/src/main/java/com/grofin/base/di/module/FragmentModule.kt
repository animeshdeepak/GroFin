package com.grofin.base.di.module

import com.grofin.base.di.qualifire.PerFragment
import com.grofin.feature.dashboard.ui.HomeFragment
import com.grofin.feature.dashboard.network.NetworkFragment
import com.grofin.feature.dashboard.rewards.RewardsFragment
import com.grofin.feature.dashboard.service.ServiceFragment
import com.grofin.feature.login.LoginFragment
import com.grofin.feature.login.OTPFragment
import com.grofin.feature.register.RegisterFragment
import com.grofin.feature.webview.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {
    @ContributesAndroidInjector
    @PerFragment
    abstract fun splashFragment(): HomeFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun otpFragment(): OTPFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun webViewFragment(): WebViewFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun insuranceFragment(): NetworkFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun rewardsFragment(): RewardsFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun serviceFragment(): ServiceFragment
}