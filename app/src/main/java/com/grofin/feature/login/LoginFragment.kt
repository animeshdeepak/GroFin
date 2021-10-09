package com.grofin.feature.login

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isMobileValid
import com.grofin.base.extensions.observe
import com.grofin.databinding.FragmentLoginBinding
import com.grofin.feature.response.LoginResponse

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override fun getLayoutId() = R.layout.fragment_login

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initViews()
        initListener()
        setUpObserver()
    }


    override fun initViews() {
        binding.loginViewModel = viewModel
        binding.errorMobileVisibility = viewModel.errorMobileVisibility
    }

    override fun setUpObserver() {
        observe(viewModel.loginMobileNoListener, ::mobileNoChangeListener)
        observe(viewModel.apiLogin, ::onLoginResponseSuccess)
    }

    private fun mobileNoChangeListener(mobileNo: String) {
        if (mobileNo.trim().isMobileValid()) {
            binding.btnGetOtp.isEnabled = true
            binding.btnGetOtp.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grofin_green
                )
            )
            binding.etMobile.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_rounded)
            viewModel.errorMobileVisibility.set(false)
        } else {
            binding.btnGetOtp.isEnabled = false
            binding.btnGetOtp.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grofin_light_green
                )
            )
            binding.etMobile.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.error_edit_text_rounded)
            viewModel.errorMobileVisibility.set(true)
        }
    }

    private fun onLoginResponseSuccess(event: SingleEvent<LoginResponse>) {
        if (isAdded && isVisible && activity != null)
            event.contentIfNotHandled?.let {
                if (it.success) {
                    navigateToOTPFragment(it.data?.id, it.data?.otp)
                } else
                    showToastMessage(it.message)
            }
    }

    override fun initListener() {
        binding.btnGetOtp.setOnClickListener {
            viewModel.login()
            it.closeKeyboard()
        }

        binding.tvRegister.setOnClickListener {
            navController().currentDestination?.getAction(R.id.action_loginFragment_to_registerFragment)
                ?.let {
                    navController().navigate(R.id.action_loginFragment_to_registerFragment)
                }
        }
    }

    private fun navigateToOTPFragment(id: Int?, otp: String?) {
        navController().currentDestination?.getAction(R.id.action_global_OTPFragment)
            ?.let {
                val action = OTPFragmentDirections.actionGlobalOTPFragment(id ?: -1, otp, binding.etMobile.text.toString().trim())
                navController().navigate(action)
            }
    }
}