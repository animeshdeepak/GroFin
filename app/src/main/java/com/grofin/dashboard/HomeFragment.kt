package com.grofin.dashboard

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentHomeBinding
import com.grofin.request.User

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getLayoutId() = R.layout.fragment_home

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        setUpObserver()
        binding.btnClick.setOnClickListener {
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