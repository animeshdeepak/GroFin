package com.grofin.splash

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.grofin.R
import com.grofin.base.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_splash

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        addFragment()
    }

    private fun addFragment() {
        val splashFragment = SplashFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, splashFragment).commit()
    }
}