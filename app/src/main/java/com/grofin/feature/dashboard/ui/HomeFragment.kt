package com.grofin.feature.dashboard.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentHomeBinding
import com.grofin.feature.dashboard.HomeViewModel
import com.grofin.feature.dashboard.ViewPagerFragmentAdapter
import com.grofin.feature.dashboard.network.NetworkFragment
import com.grofin.feature.dashboard.rewards.RewardsFragment
import com.grofin.feature.dashboard.service.ServiceFragment
import com.grofin.feature.request.User

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object {
        private const val TAB_POSITION_1 = 0
        private const val TAB_POSITION_2 = 1
        private const val TAB_POSITION_3 = 2
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initListener()
        setUpObserver()
        initViews()

        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpViewPager() {
        val fragmentList = arrayListOf<Fragment>(
            ServiceFragment.newInstance(),
            NetworkFragment.newInstance(),
            RewardsFragment.newInstance()
        )
        binding.viewPager.apply {
            offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
//            isUserInputEnabled = false
            isSaveEnabled = false
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = ViewPagerFragmentAdapter(childFragmentManager, lifecycle, fragmentList)
        }
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(
            binding.tabs,
            binding.viewPager
        ) { tabs, position ->
            when (position) {
                TAB_POSITION_1 -> tabs.text = getString(R.string.service_tab)
                TAB_POSITION_2 -> tabs.text = getString(R.string.network_tab)
                TAB_POSITION_3 -> tabs.text = getString(R.string.rewards_tab)
            }
        }.attach()
    }

    override fun initViews() {

    }

    override fun setUpObserver() {
        observe(viewModel.apiUser, ::onUserSuccess)
    }

    override fun initListener() {
//            viewModel.getSingleUser()
    }

    private fun onUserSuccess(event: SingleEvent<User>) {
        event.contentIfNotHandled?.let {
            showToastMessage(it.data?.email)
        }
    }
}