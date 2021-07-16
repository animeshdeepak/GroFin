package com.grofin.feature.dashboard

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentHomeBinding
import com.grofin.feature.request.User

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getLayoutId() = R.layout.fragment_home

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {

    }

    override fun initViews() = Unit

    override fun setUpObserver() {
        observe(viewModel.apiUser, ::onUserSuccess)
    }

    override fun initListener() {
        binding.btnClick.setOnClickListener {
            viewModel.getSingleUser()
        }
    }

    private fun onUserSuccess(event: SingleEvent<User>) {
        event.contentIfNotHandled?.let {
            showToastMessage(it.data?.email)
        }
    }
}