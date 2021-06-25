package com.grofin

import android.os.Bundle
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentSplashBinding
import com.grofin.request.User

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun getLayoutId() = R.layout.fragment_splash

    override fun getViewModelClass() = SplashViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        setUpObserver()
        binding.button.setOnClickListener {
            viewModel.getSingleUser()
        }
    }

    private fun setUpObserver() {
        observe(viewModel.apiUser, ::onUserSuccess)
    }

    private fun onUserSuccess(event: SingleEvent<User>) {
        event.contentIfNotHandled?.let {
            showToastMessage(it.data?.email)
        }
    }
}