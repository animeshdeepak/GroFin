package com.grofin.feature.register

import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.*
import com.grofin.databinding.FragmentRegisterBinding
import com.grofin.feature.login.LoginViewModel
import com.grofin.feature.response.RegisterResponse


class RegisterFragment : BaseFragment<FragmentRegisterBinding, LoginViewModel>() {

    override fun getLayoutId() = R.layout.fragment_register

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initViews()
        initListener()
        setUpObserver()
    }

    override fun initViews() {
        binding.errorMobileVisibility = viewModel.errorMobileVisibilityRegister
        binding.errorReferralVisibility = viewModel.errorReferralVisibility

        configTermsNConditionCheckbox()
        removeRippleEffectFromCheckBox(binding.termsConditionCb)
    }

    private fun removeRippleEffectFromCheckBox(checkBox: CheckBox) {
        var drawable: Drawable? = checkBox.background
        if (drawable is RippleDrawable) {
            drawable = drawable.findDrawableByLayerId(0)
            checkBox.background = drawable
        }
    }

    private fun configTermsNConditionCheckbox() {
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Prevent CheckBox state from being toggled when link is clicked
                widget.cancelPendingInputEvents()
                // Do action for link text...
                "clicked".showToast(requireContext())
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                // Show links with underlines (optional)
                ds.color = ContextCompat.getColor(requireContext(), R.color.grofin_blue)
                ds.isUnderlineText = true
            }
        }
        val linkText = SpannableString("Terms and Conditions")
        linkText.setSpan(clickableSpan, 0, linkText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val cs = TextUtils.expandTemplate(
            "I agree with ^1", linkText
        )

        binding.termsConditionCb.text = cs
        // Finally, make links clickable
        binding.termsConditionCb.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun initListener() {
        binding.btnGetOtp.setOnClickListener {
            binding.enterPhoneNoEt.let {
                if (it.text.toString().isMobileValid()) {
                    viewModel.errorMobileVisibilityRegister.set(false)
                    it.closeKeyboard()
                    viewModel.register(it.text.toString())
                } else
                    viewModel.errorMobileVisibilityRegister.set(true)
            }
        }
    }

    override fun setUpObserver() {
        observe(viewModel.apiRegister, ::onRegisterResponseSuccess)
    }

    private fun onRegisterResponseSuccess(event: SingleEvent<RegisterResponse>) {
        event.contentIfNotHandled?.let {
            if (it.success) {
                navigateToLoginFragment()
            } else {
                showToastMessage(it.message)
            }
        }
    }

    private fun navigateToLoginFragment() {
        navController().currentDestination?.getAction(R.id.action_global_OTPFragment)
            ?.let {
                val bundle =
                    bundleOf(Constants.KEY_MOBILE_NUMBER to binding.enterPhoneNoEt.text.toString())
                navController().navigate(R.id.action_global_OTPFragment, bundle)
            }
    }

    private fun callLoginAPI(mobile: String, otp: String) {
        showToastMessage("$mobile $otp")
    }
}