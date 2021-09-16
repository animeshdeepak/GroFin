package com.grofin.feature.register

import android.os.Bundle
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.databinding.FragmentRegisterBinding
import com.grofin.feature.login.LoginViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding, LoginViewModel>() {

    override fun getLayoutId() = R.layout.fragment_register

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initViews()
        initListener()
    }

    override fun initViews() {
        binding.errorMobileVisibility = viewModel.errorMobileVisibilityRegister
        binding.errorReferralVisibility = viewModel.errorReferralVisibility
    }

    override fun initListener() {
        binding.btnGetOtp.setOnClickListener {
            binding.enterPhoneNoEt.let {
                if (it.text.toString().isMobileValid()) {
                    viewModel.errorMobileVisibilityRegister.set(false)
                    it.closeKeyboard()
                    navigateToLoginFragment()
                } else
                    viewModel.errorMobileVisibilityRegister.set(true)
            }
        }
    }

    override fun setUpObserver() = Unit

    private fun navigateToLoginFragment() {
        navController().currentDestination?.getAction(R.id.action_global_OTPFragment)
            ?.let {
                val bundle = bundleOf(Constants.MOBILE_NUMBER to binding.enterPhoneNoEt.text.toString())
                navController().navigate(R.id.action_global_OTPFragment, bundle)
            }
    }

    private fun callLoginAPI(mobile: String, otp: String) {
        showToastMessage("$mobile $otp")
    }
}