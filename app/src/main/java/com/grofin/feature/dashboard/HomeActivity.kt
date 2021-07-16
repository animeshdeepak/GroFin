package com.grofin.feature.dashboard

import android.os.Bundle
import android.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar
import com.grofin.R
import com.grofin.base.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_home

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        findViewById<MaterialToolbar>(R.id.topAppBar).apply {
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }
}