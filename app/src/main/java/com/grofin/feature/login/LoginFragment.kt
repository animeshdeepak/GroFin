package com.grofin.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.databinding.FragmentLoginBinding
import com.grofin.feature.dashboard.HomeActivity
import com.grofin.feature.login.OTPBottomSheetFragment.Companion.OTP_BOTTOM_SHEET

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
            binding.etMobile.editText?.let {
                if (it.text.toString().isMobileValid()) {
                    viewModel.errorMobileVisibility.set(false)
                    it.closeKeyboard()
                    openBottomSheetFragment()
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

    private fun openBottomSheetFragment() {
        childFragmentManager.let {
            OTPBottomSheetFragment.newInstance(Bundle()).apply {
                isCancelable = false
                show(it, OTP_BOTTOM_SHEET)
                onLoginClick = { otp ->
                    callLoginAPI(binding.etMobile.editText?.text.toString(), otp)
                }
            }
        }
    }

    private fun callLoginAPI(mobile: String, otp: String) {
        showToastMessage("$mobile $otp")
        launchHomeActivity()
    }

    private fun launchHomeActivity() {
        Intent(requireContext(), HomeActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }
}