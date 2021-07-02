package com.grofin.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.grofin.R
import com.grofin.base.extensions.closeKeyboard
import com.grofin.base.extensions.getDurationString
import com.grofin.base.extensions.isOtpValid
import com.grofin.base.extensions.showToast
import com.grofin.databinding.OtpBottomSheetFragmentBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

class OTPBottomSheetFragment : BottomSheetDialogFragment() {
    var onLoginClick: ((otp: String) -> Unit)? = null
    var errorOTPVisibility = ObservableBoolean()

    companion object {
        const val TIMER_VALUE = 15L
        const val OTP_BOTTOM_SHEET = "otp_bottom_sheet"

        fun newInstance(bundle: Bundle): OTPBottomSheetFragment {
            val fragment = OTPBottomSheetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: OtpBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.otp_bottom_sheet_fragment,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorOTPVisibility = errorOTPVisibility
        setUpViews()
    }

    private fun setUpViews() {
        startResendCountDownTimer()

        binding.btnLogin.setOnClickListener {
            if (binding.etOtp.editText?.text.toString().isOtpValid()) {
                errorOTPVisibility.set(false)
                binding.etOtp.editText?.closeKeyboard()
                onLoginClick?.invoke(binding.etOtp.editText?.text.toString())
                dismissAllowingStateLoss()
            } else
                errorOTPVisibility.set(true)
        }

        binding.tvCounter.setOnClickListener {
            if (binding.tvCounter.text == getString(R.string.resend)) {
                "resend clicked!".showToast(requireContext())
                startResendCountDownTimer()
            }
        }
    }

    private fun startResendCountDownTimer() {
        Observable.interval(1, TimeUnit.SECONDS)
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
            .subscribe()
    }
}