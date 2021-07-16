package com.grofin.feature.login

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.grofin.R
import com.grofin.base.base.BaseActivity
import com.grofin.base.constants.Constants

class LoginRegisterActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_login_register

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.login_register_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.login_register_nav_graph)

        val bundle = bundleOf(
            Constants.TOOLBAR_TITLE to getString(R.string.title_login)
        )
        navController.setGraph(navGraph, bundle)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            arguments?.apply {
                setToolbarTitle(getString(Constants.TOOLBAR_TITLE, ""))
            }
        }
    }
}