package com.grofin.feature.webview


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.networking.Connectivity
import com.grofin.databinding.FragmentWebViewBinding
import com.grofin.feature.dashboard.HomeActivity
import com.grofin.feature.webview.WebViewActivity.Companion.WEB_VIEW_URL

class WebViewFragment : BaseFragment<FragmentWebViewBinding, WebViewViewModel>() {
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

    override fun executeOnlyOnce() {
        initListener()
        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        url = requireArguments().getString(WEB_VIEW_URL, "")

        binding.webView.apply {
            webChromeClient = WebChromeClient()

            loadWebUrl()
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
            }

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
