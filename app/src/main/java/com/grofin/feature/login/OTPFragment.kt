package com.grofin.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableBoolean
import androidx.navigation.fragment.navArgs
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.*
import com.grofin.databinding.OtpFragmentBinding
import com.grofin.feature.dashboard.HomeActivity
import com.grofin.feature.response.OTPResponse
import com.grofin.feature.response.ResendOTPResponse
import com.grofin.feature.response.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class OTPFragment : BaseFragment<OtpFragmentBinding, LoginViewModel>() {
    private var errorOTPVisibility = ObservableBoolean()
    private var timerVisibility = ObservableBoolean(true)

    private val args: OTPFragmentArgs by navArgs()

    companion object {
        const val TIMER_VALUE = 10L
    }

    override fun getLayoutId() = R.layout.otp_fragment

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    private fun startResendCountDownTimer() {
        Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (isAdded && isVisible) {
                    binding.timerTv.text = (TIMER_VALUE - it).getDurationString()
                    timerVisibility.set(true)
                }
            }
            .takeUntil {
                it == TIMER_VALUE
            }
            .doOnComplete {
                if (isAdded && isVisible)
                    timerVisibility.set(false)
            }
            .subscribe()
    }

    override fun executeOnlyOnce() {
        initViews()
        initListener()
        setUpObserver()
    }

    override fun initViews() {
        binding.viewModel = viewModel
        binding.errorOTPVisibility = errorOTPVisibility
        binding.timerVisibility = timerVisibility
        startResendCountDownTimer()
    }

    override fun initListener() {
        binding.verifyBtn.setOnClickListener {
            viewModel.validateOTP(args.id)
            binding.otpEt.closeKeyboard()
        }

        binding.resendTv.setOnClickListener {
            viewModel.resendOTP(args.id)
            startResendCountDownTimer()
        }
    }

    override fun setUpObserver() {
        observe(viewModel.otpListener, ::onOTPChange)
        observe(viewModel.apiOTP, ::onOTPResponseSuccess)
        observe(viewModel.apiResendOTP, ::onResendOTPResponseSuccess)
        observe(viewModel.apiUser, ::onUserResponseSuccess)
    }

    private fun onOTPChange(otp: String) {
        if (otp.trim().isOtpValid()) {
            binding.verifyBtn.isEnabled = true
            binding.verifyBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grofin_green
                )
            )
            binding.otpEt.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_rounded)
            errorOTPVisibility.set(false)
        } else {
            binding.verifyBtn.isEnabled = false
            binding.verifyBtn.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.grofin_light_green
                )
            )
            binding.otpEt.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.error_edit_text_rounded)
            errorOTPVisibility.set(true)
        }
    }

    private fun onOTPResponseSuccess(event: SingleEvent<OTPResponse>) {
        if (isAdded && isVisible && activity != null)
            event.contentIfNotHandled?.let {
                if (it.success) {
                    viewModel.getUser()
                    /**
                     * TODO(change in future add below line to getuser response)
                     */
                    launchHomeActivity()
                } else
                    showToastMessage(it.message)
            }
    }

    private fun onResendOTPResponseSuccess(event: SingleEvent<ResendOTPResponse>) {
        if (isAdded && isVisible && activity != null) {
            event.contentIfNotHandled?.let {
                if (it.success) {
                    /**
                     * TODO(handle resend otp response)
                     */
                } else
                    showToastMessage(it.message)
            }
        }
    }

    private fun onUserResponseSuccess(event: SingleEvent<UserResponse>) {
        event.contentIfNotHandled?.let {
            if (it.success)
                launchHomeActivity()
            else
                showToastMessage(it.message)
        }
    }

    private fun launchHomeActivity() {
        Intent(requireContext(), HomeActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }
}