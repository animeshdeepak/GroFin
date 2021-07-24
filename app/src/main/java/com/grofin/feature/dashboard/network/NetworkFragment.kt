package com.grofin.feature.dashboard.network

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentNetworkBinding
import com.grofin.feature.dashboard.HomeViewModel

class NetworkFragment : BaseFragment<FragmentNetworkBinding, HomeViewModel>() {

    companion object {
        fun newInstance(): NetworkFragment = NetworkFragment()
    }

    override fun getLayoutId() = R.layout.fragment_network

    override fun getViewModelClass() = HomeViewModel::class.java

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