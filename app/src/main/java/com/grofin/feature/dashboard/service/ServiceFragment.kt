package com.grofin.feature.dashboard.service

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentServiceBinding

class ServiceFragment : BaseFragment<FragmentServiceBinding, ServiceViewModel>() {
    companion object {
        fun newInstance(): ServiceFragment = ServiceFragment()
    }

    override fun getLayoutId() = R.layout.fragment_service

    override fun getViewModelClass() = ServiceViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {

    }

    override fun initViews() {

    }

    override fun initListener() {

    }

    override fun setUpObserver() {

    }
}