package com.grofin.feature.login

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getLayoutId() = R.layout.fragment_login

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        binding.errorMobileVisibility = viewModel.errorMobileVisibility
        initListener()
        setUpObserver()
    }


    override fun initViews() = Unit

    override fun setUpObserver() {
        observe(viewModel.enableNextBtn, ::mobileNoChangeListener)
    }

    private fun mobileNoChangeListener(mobileNo: String) {
        if (mobileNo.trim().isMobileValid()) {
            binding.btnGetOtp.isEnabled = true
            binding.btnGetOtp.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grofin_green))
            binding.etMobile.background = ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_rounded)
            viewModel.errorMobileVisibility.set(false)
        } else {
            binding.btnGetOtp.isEnabled = false
            binding.btnGetOtp.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.grofin_light_green))
            binding.etMobile.background = ContextCompat.getDrawable(requireContext(), R.drawable.error_edit_text_rounded)
            viewModel.errorMobileVisibility.set(true)
        }
    }

    override fun initListener() {
        binding.loginViewModel = viewModel

        binding.btnGetOtp.setOnClickListener {
            /*binding.etMobile.let {
                if (it.text.toString().isMobileValid()) {*/
                    it.closeKeyboard()
                    navigateToOTPFragment()
                /*} else
                    viewModel.errorMobileVisibility.set(true)
            }*/
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
                val bundle =
                    bundleOf(Constants.KEY_MOBILE_NUMBER to binding.etMobile.text.toString())
                navController().navigate(R.id.action_global_OTPFragment, bundle)
            }
    }
}