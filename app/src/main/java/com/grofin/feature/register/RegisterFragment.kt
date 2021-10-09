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
import androidx.navigation.NavDirections
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.*
import com.grofin.databinding.FragmentRegisterBinding
import com.grofin.feature.login.LoginViewModel
import com.grofin.feature.login.OTPFragmentDirections
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
        binding.viewModel = viewModel
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
        val linkText = SpannableString(getString(R.string.terms_n_conditions))
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
            viewModel.register()
            it.closeKeyboard()
        }

        binding.termsConditionCb.setOnCheckedChangeListener { _, isChecked ->
            viewModel.registerCheckboxListener.value = isChecked
        }
    }

    override fun setUpObserver() {
        observe(viewModel.apiRegister, ::onRegisterResponseSuccess)
        observe(viewModel.registerReferralNoListener, ::onReferralNoChange)
        observe(viewModel.registerMobileNoListener, ::onMobileNoChange)
        observe(viewModel.registerCheckboxListener, ::onCheckboxChange)
    }

    private fun onRegisterResponseSuccess(event: SingleEvent<RegisterResponse>) {
        event.contentIfNotHandled?.let {
            if (it.success) {
                navigateToLoginFragment(it.data?.id, it.data?.otp)
            } else
                showToastMessage(it.message)
        }
    }

    private fun onReferralNoChange(referralNo: String) {
        enableRegisterNextBtn(binding.etRefer)
    }

    private fun onMobileNoChange(mobileMo: String) {
        enableRegisterNextBtn(binding.enterPhoneNoEt)
    }

    private fun onCheckboxChange(isChecked: Boolean) {
        enableRegisterNextBtn(binding.termsConditionCb)
    }

    private fun enableRegisterNextBtn(view: View) {
        when (view) {
            binding.etRefer -> {
                if (viewModel.registerReferralNoListener.value?.isReferIdValid() == true) {
                    binding.etRefer.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_rounded)
                    viewModel.errorReferralVisibility.set(false)
                } else {
                    binding.etRefer.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.error_edit_text_rounded
                        )
                    viewModel.errorReferralVisibility.set(true)
                }
            }

            binding.enterPhoneNoEt -> {
                if (viewModel.registerMobileNoListener.value?.isMobileValid() == true) {
                    binding.enterPhoneNoEt.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_rounded)
                    viewModel.errorMobileVisibilityRegister.set(false)
                } else {
                    binding.enterPhoneNoEt.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.error_edit_text_rounded
                        )
                    viewModel.errorMobileVisibilityRegister.set(true)
                }
            }

            binding.termsConditionCb -> {
                if (viewModel.registerDataValidation()) {
                    binding.btnGetOtp.isEnabled = true
                    binding.btnGetOtp.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grofin_green
                        )
                    )
                } else {
                    binding.btnGetOtp.isEnabled = false
                    binding.btnGetOtp.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.grofin_light_green
                        )
                    )
                }
            }
        }
    }

    private fun navigateToLoginFragment(id: Int?, otp: String?) {
        navController().currentDestination?.getAction(R.id.action_global_OTPFragment)
            ?.let {
                val action: NavDirections = if (binding.etRefer.text.toString().trim()
                        .isEmpty() || binding.etRefer.text.toString().trim().isBlank()
                )
                    OTPFragmentDirections.actionGlobalOTPFragment(
                        id ?: -1,
                        otp,
                        binding.enterPhoneNoEt.text.toString().trim()
                    )
                else
                    OTPFragmentDirections.actionGlobalOTPFragment(
                        id ?: -1,
                        otp,
                        binding.enterPhoneNoEt.text.toString().trim(),
                        binding.etRefer.text.toString().trim()
                    )
                navController().navigate(action)
            }
    }
}