package com.grofin.feature.dashboard

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.grofin.R
import com.grofin.base.base.BaseActivity
import com.grofin.base.constants.Constants

class HomeActivity : BaseActivity() {
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView


    override fun getLayoutId() = R.layout.activity_home

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) {
        initView()
        setUpDrawer()
        initDrawerHeader()
        setUpNavigation()
    }

    private fun initView() {
        drawer = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.topAppBar)
        navigationView = findViewById(R.id.navigation_view)
    }

    private fun setUpDrawer() {
        drawerToggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        navigationView.setNavigationItemSelectedListener { item ->
            onNavigationItemSelectedHome(item)
        }
    }

    private fun initDrawerHeader() {
        val header = navigationView.getHeaderView(0)
        header.findViewById<ImageView>(R.id.iv_profile).run {
            setOnClickListener {
                showToastMessage("profile")
            }
        }

        header.findViewById<TextView>(R.id.tv_name).run {
            text = "Mr. Azim"
            setOnClickListener {
                showToastMessage("Azim")
            }
        }

        header.findViewById<TextView>(R.id.tv_email).run {
            text = "azim@gmail.com"
            setOnClickListener {
                showToastMessage("gmail")
            }
        }
    }

    private fun onNavigationItemSelectedHome(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_faq -> showToastMessage("faq")
            R.id.action_about_us -> showToastMessage("about us")
            R.id.action_support -> showToastMessage("support")
            R.id.action_tnc -> showToastMessage("tnc")
            R.id.action_share -> showToastMessage("share")
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.home_nav_graph)
        navController.setGraph(navGraph, bundleOf())

        navController.addOnDestinationChangedListener { _, _, arguments ->
            arguments?.apply {
                setToolbarTitle(getString(Constants.TOOLBAR_TITLE, ""))
            }
        }
    }
}