package com.grofin.feature.webview


import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.networking.Connectivity
import com.grofin.databinding.FragmentWebViewBinding
import com.grofin.feature.dashboard.HomeActivity

class WebViewFragment : BaseFragment<FragmentWebViewBinding, WebViewViewModel>() {

    companion object {
        const val URL = "url"
        const val TITLE = "title"
    }

    override fun getLayoutId() = R.layout.fragment_web_view

    override fun initViews() {
        binding.webViewNotLoad = viewModel.retryBtnVisibility
        binding.loader = viewModel.loaderVisibility
    }

    override fun initListener() {
        binding.btnRetry.setOnClickListener {
            loadWebUrl()
        }
    }

    override fun setUpObserver() = Unit

    override fun getViewModelClass() = WebViewViewModel::class.java

    private var url: String? = null
    private var title: String? = null


    override fun executeOnlyOnce() {
        initListener()
        initViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        url = requireArguments().getString(URL, "")
        title = requireArguments().getString(TITLE, "")

        binding.webView.apply {
            loadWebUrl()
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    viewModel.loaderVisibility.set(false)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    if (isAdded && activity != null) {
                        viewModel.loaderVisibility.set(false)
                        viewModel.retryBtnVisibility.set(true)
                    }
                }
            }
        }
    }

    private fun loadWebUrl() {
        binding.apply {
            viewModel.loaderVisibility.set(true)

            if (Connectivity.isNetworkConnected(requireContext())) {
                viewModel.retryBtnVisibility.set(false)
                webView.loadUrl(url.orEmpty())
            } else {
                viewModel.loaderVisibility.set(false)
                viewModel.retryBtnVisibility.set(true)
            }
        }
    }
}
