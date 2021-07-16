package com.grofin.feature.dashboard

import android.os.Bundle
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentHomeBinding
import com.grofin.feature.request.User
import com.grofin.feature.webview.WebViewFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override fun getLayoutId() = R.layout.fragment_home

    override fun getViewModelClass() = HomeViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initListener()
        setUpObserver()
        initViews()
    }

    override fun initViews() {

    }

    override fun setUpObserver() {
        observe(viewModel.apiUser, ::onUserSuccess)
    }

    override fun initListener() {
        binding.btnClick.setOnClickListener {
//            viewModel.getSingleUser()
            navController().currentDestination?.getAction(R.id.action_homeFragment_to_webViewFragment)?.let {
                val bundle = bundleOf(
                    WebViewFragment.URL to "https://www.google.com/",
                    Constants.TOOLBAR_TITLE to "WebView"
                )
                navController().navigate(R.id.action_homeFragment_to_webViewFragment, bundle)
            }
        }
    }

    private fun onUserSuccess(event: SingleEvent<User>) {
        event.contentIfNotHandled?.let {
            showToastMessage(it.data?.email)
        }
    }
}