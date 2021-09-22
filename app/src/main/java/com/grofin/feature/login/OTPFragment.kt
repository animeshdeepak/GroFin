package com.grofin.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.constants.Constants
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.getDurationString
import com.grofin.base.extensions.isOtpValid
import com.grofin.base.extensions.showToast
import com.grofin.databinding.OtpFragmentBinding
import com.grofin.feature.dashboard.HomeActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class OTPFragment : BaseFragment<OtpFragmentBinding, LoginViewModel>() {
    private var errorOTPVisibility = ObservableBoolean()
    private var timerVisibility = ObservableBoolean(true)

    private var phoneNumber: String? = null

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
        setUpArguments()
        initViews()
        initListener()
    }

    private fun setUpArguments() {
        phoneNumber = arguments?.getString(Constants.KEY_MOBILE_NUMBER, "")
    }

    override fun initViews() {
        binding.errorOTPVisibility = errorOTPVisibility
        binding.timerVisibility = timerVisibility
        startResendCountDownTimer()
    }

    override fun initListener() {
        binding.verifyBtn.setOnClickListener {
            if (binding.otpEt.text.toString().isOtpValid()) {
                errorOTPVisibility.set(false)
                binding.otpEt.closeKeyboard()
                callLoginAPI(binding.otpEt.text.toString())
            } else
                errorOTPVisibility.set(true)
        }

        binding.resendTv.setOnClickListener {
            "resend clicked!".showToast(requireContext())
            startResendCountDownTimer()
        }
    }

    override fun setUpObserver() = Unit

    private fun callLoginAPI(otp: String) {
        phoneNumber?.let {
            showToastMessage("$it $otp")
        }
        launchHomeActivity()
    }

    private fun launchHomeActivity() {
        Intent(requireContext(), HomeActivity::class.java).apply {
            startActivity(this)
        }
        activity?.finish()
    }
}