package com.grofin.dashboard

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseActivity

class HomeActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_home

    override fun setSplashScreen() {
//        Thread.sleep(3000)
        setTheme(R.style.Theme_GroFin)
    }

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit
}