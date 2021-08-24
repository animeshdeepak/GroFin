package com.grofin.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ObservableBoolean
import com.grofin.R
import com.grofin.base.base.BaseFragment
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.isOtpValid
import com.grofin.databinding.OtpFragmentBinding
import com.grofin.feature.dashboard.HomeActivity

class OTPFragment : BaseFragment<OtpFragmentBinding, LoginViewModel>() {
    var onLoginClick: ((otp: String) -> Unit)? = null
    var errorOTPVisibility = ObservableBoolean()

    companion object {
        const val TIMER_VALUE = 15L
        const val OTP_BOTTOM_SHEET = "otp_bottom_sheet"

        fun newInstance(bundle: Bundle): OTPFragment {
            val fragment = OTPFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun startResendCountDownTimer() {
/*        Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                if (isAdded && isVisible)
                    binding.tvCounter.text = (TIMER_VALUE - 1 - it).getDurationString()
            }
            .takeUntil {
                it == TIMER_VALUE
            }
            .doOnComplete {
                if (isAdded && isVisible)
                    binding.tvCounter.text = getString(R.string.resend)
            }
            .subscribe()*/
    }

    override fun getLayoutId() = R.layout.otp_fragment

    override fun getViewModelClass() = LoginViewModel::class.java

    override fun performTasksOnActivityCreated(savedInstanceState: Bundle?) = Unit

    override fun executeOnlyOnce() {
        initViews()
        initListener()
    }

    override fun initViews() {
        binding.errorOTPVisibility = errorOTPVisibility
        startResendCountDownTimer()
    }

    override fun initListener() {
        binding.verifyBtn.setOnClickListener {
            if (binding.otpEt.text.toString().isOtpValid()) {
                errorOTPVisibility.set(false)
                binding.otpEt.closeKeyboard()
                callLoginAPI("1234567890", binding.otpEt.text.toString())
            } else
                errorOTPVisibility.set(true)
        }

/*        binding.tvCounter.setOnClickListener {
            if (binding.tvCounter.text == getString(R.string.resend)) {
                "resend clicked!".showToast(requireContext())
                startResendCountDownTimer()
            }
        }*/
    }

    override fun setUpObserver() = Unit

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