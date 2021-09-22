package com.grofin.feature.login

import android.os.Bundle
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getLayoutId() = R.layout.fragment_login

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        binding.errorMobileVisibility = viewModel.errorMobileVisibility
        initListener()
    }


    override fun initViews() = Unit

    override fun setUpObserver() = Unit

    override fun initListener() {
        binding.btnGetOtp.setOnClickListener {
            binding.etMobile.let {
                if (it.text.toString().isMobileValid()) {
                    viewModel.errorMobileVisibility.set(false)
                    it.closeKeyboard()
                    navigateToOTPFragment()
                } else
                    viewModel.errorMobileVisibility.set(true)
            }
        }

        binding.tvRegister.setOnClickListener {
            navController().currentDestination?.getAction(R.id.action_loginFragment_to_registerFragment)
                ?.let {
                    val bundle =
                        bundleOf(Constants.TOOLBAR_TITLE to getString(R.string.title_register))
                    navController().navigate(R.id.action_loginFragment_to_registerFragment, bundle)
                }
        }
    }

    private fun navigateToOTPFragment() {
        navController().currentDestination?.getAction(R.id.action_global_OTPFragment)
            ?.let {
                val bundle = bundleOf(Constants.KEY_MOBILE_NUMBER to binding.etMobile.text.toString())
                navController().navigate(R.id.action_global_OTPFragment, bundle)
            }
    }
}