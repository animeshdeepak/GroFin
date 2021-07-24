package com.grofin.feature.dashboard.rewards

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.databinding.FragmentRewardsBinding
import com.grofin.feature.dashboard.HomeViewModel

class RewardsFragment : BaseFragment<FragmentRewardsBinding, HomeViewModel>() {
    companion object {
        fun newInstance(): RewardsFragment = RewardsFragment()
    }

    override fun getLayoutId() = R.layout.fragment_rewards

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