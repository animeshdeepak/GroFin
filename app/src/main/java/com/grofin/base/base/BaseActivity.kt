package com.grofin.base.base

import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grofin.R
import com.grofin.base.extensions.gone
import com.grofin.base.extensions.show
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var progressBarView: RelativeLayout

    abstract fun getLayoutId(): Int
    abstract fun performTasksOnActivityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setUpProgressView()
        performTasksOnActivityCreated(savedInstanceState)
    }

    inline fun <reified T : ViewModel> FragmentActivity.viewModel(factory: ViewModelProvider.Factory): T {
        return ViewModelProvider(this.viewModelStore, factory)[T::class.java]
    }

    fun showToastMessage(message: String?) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, getString(R.string.no_msg_found), Toast.LENGTH_SHORT).show()
    }

    private fun setUpProgressView() {
        progressBarView = findViewById(R.id.rl_progress)
        progressBarView.gone()
    }

    fun progressBarVisibility(flag: Boolean) {
        if (flag)
            progressBarView.show()
        else
            progressBarView.gone()
    }
}