package com.grofin

import android.os.Bundle
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun getLayoutId() = R.layout.fragment_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() = showToastMessage("Great it worked :)")

    override fun setObserver() {

    }
}