package com.grofin.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.grofin.R
import com.grofin.base.base.BaseActivity
import com.grofin.feature.login.LoginRegisterActivity

class SplashActivity : BaseActivity() {
    companion object {
        const val SPLASH_TIMER_VALUE = 2000L
    }

    override fun getLayoutId() = R.layout.activity_splash

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            launchHomeActivity()
        }, SPLASH_TIMER_VALUE)
    }

    private fun launchHomeActivity() {
        Intent(this, LoginRegisterActivity::class.java).also {
            startActivity(it)
        }
        this.finish()
    }
}