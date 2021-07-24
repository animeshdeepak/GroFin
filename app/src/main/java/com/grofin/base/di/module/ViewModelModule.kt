package com.grofin.base.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grofin.base.di.ViewModelFactory
import com.grofin.base.di.qualifire.ViewModelKey
import com.grofin.feature.dashboard.HomeViewModel
import com.grofin.feature.dashboard.network.NetworkViewModel
import com.grofin.feature.dashboard.rewards.RewardsViewModel
import com.grofin.feature.dashboard.service.ServiceViewModel
import com.grofin.feature.login.LoginViewModel
import com.grofin.feature.webview.WebViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindsHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindsLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel::class)
    internal abstract fun bindsWebViewViewModel(viewModel: WebViewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ServiceViewModel::class)
    internal abstract fun bindsServiceViewModel(viewModel: ServiceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RewardsViewModel::class)
    internal abstract fun bindsRewardsViewModel(viewModel: RewardsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NetworkViewModel::class)
    internal abstract fun bindsNetworkViewModel(viewModel: NetworkViewModel): ViewModel
}