package com.grofin.feature.dashboard.service

import android.content.Intent
import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.databinding.FragmentServiceBinding
import com.grofin.feature.webview.WebViewActivity

class ServiceFragment : BaseFragment<FragmentServiceBinding, ServiceViewModel>() {
    companion object {
        fun newInstance(): ServiceFragment = ServiceFragment()
    }

    override fun getLayoutId() = R.layout.fragment_service

    override fun getViewModelClass() = ServiceViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initListener()
    }

    override fun initViews() {

    }

    override fun initListener() {
        binding.btnWebView.setOnClickListener {
            Intent(requireContext(), WebViewActivity::class.java).apply {
                putExtra(WebViewActivity.WEB_VIEW_TITLE, getString(R.string.title_motor))
                putExtra(WebViewActivity.WEB_VIEW_URL, Constants.MOTORS_URL)
            }.run {
                startActivity(this)
            }
        }
    }

    override fun setUpObserver() {

    }
}