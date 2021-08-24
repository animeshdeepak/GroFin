package com.grofin.feature.register

import android.os.Bundle
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.databinding.FragmentRegisterBinding
import com.grofin.feature.login.LoginViewModel
import com.grofin.feature.login.OTPFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding, LoginViewModel>() {

    override fun getLayoutId() = R.layout.fragment_register

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        binding.errorMobileVisibility = viewModel.errorMobileVisibility
        binding.errorReferralVisibility = viewModel.errorReferralVisibility
    }

    override fun initViews() = Unit
    override fun setUpObserver() = Unit

    override fun initListener() {
        binding.btnGetOtp.setOnClickListener {
            binding.etMobile.editText?.let {
                if (it.text.toString().isMobileValid()) {
                    viewModel.errorMobileVisibility.set(false)
                    it.closeKeyboard()
                    openBottomSheetFragment()
                } else
                    viewModel.errorMobileVisibility.set(true)
            }
        }
    }

    private fun openBottomSheetFragment() {
/*        childFragmentManager.let {
            OTPFragment.newInstance(Bundle()).apply {
                isCancelable = false
                show(it, OTPFragment.OTP_BOTTOM_SHEET)
                onLoginClick = { otp ->
                    callLoginAPI(binding.etMobile.editText?.text.toString(), otp)
                }
            }
        }*/
    }

    private fun callLoginAPI(mobile: String, otp: String) {
        showToastMessage("$mobile $otp")
    }
}