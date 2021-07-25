package com.grofin.feature.webview

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grofin.R
import com.grofin.base.base.BaseActivity

class WebViewActivity : BaseActivity() {
    companion object {
        const val WEB_VIEW_TITLE = "web_view_title"
        const val WEB_VIEW_URL = "url"
    }

    override fun getLayoutId() = R.layout.activity_web_view

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        setUpToolbar()
        getWebViewData()
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.web_view_topAppBar)
        toolbar?.let {
            setSupportActionBar(it)
        }
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun getWebViewData() {
        val extra = intent.extras
        val title = extra?.getString(WEB_VIEW_TITLE, "")
        val url = extra?.getString(WEB_VIEW_URL, "")
        title?.let {
            if (it.isNotEmpty())
                supportActionBar?.title = title
        }

        addFragment(url)
    }

    private fun addFragment(url: String?) {
        val bundle = bundleOf(
            WEB_VIEW_URL to url
        )
        val webViewFragment = WebViewFragment()
        webViewFragment.arguments = bundle

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, webViewFragment).commit()
    }
}