package com.grofin.feature.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.extensions.isMobileValid
import com.grofin.base.extensions.isReferIdValid
import com.grofin.base.repo.LoginRegisterRepo
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.OTPResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRegisterRepo: LoginRegisterRepo) :
    BaseViewModel() {
    // for login screen
    var errorMobileVisibility = ObservableBoolean()
    val loginMobileNoListener = MutableLiveData<String>()

    // for register screen
    var errorMobileVisibilityRegister = ObservableBoolean()
    var errorReferralVisibility = ObservableBoolean()
    val registerMobileNoListener = MutableLiveData<String>()
    val registerReferralNoListener = MutableLiveData<String>()
    val registerCheckboxListener = MutableLiveData<Boolean>()

    //for otp screen
    var otpListener = MutableLiveData<String>()

    private var _apiRegister = MutableLiveData<SingleEvent<RegisterResponse>>()
    val apiRegister: LiveData<SingleEvent<RegisterResponse>>
        get() = _apiRegister

    private var _apiLogin = MutableLiveData<SingleEvent<LoginResponse>>()
    val apiLogin: LiveData<SingleEvent<LoginResponse>>
        get() = _apiLogin

    private var _apiOTP = MutableLiveData<SingleEvent<OTPResponse>>()
    val apiOTP: LiveData<SingleEvent<OTPResponse>>
        get() = _apiOTP

    fun registerDataValidation(): Boolean {
        return if (
            registerReferralNoListener.value == null ||
            registerReferralNoListener.value?.isReferIdValid() == true ||
            registerReferralNoListener.value?.isBlank() == true ||
            registerReferralNoListener.value?.isEmpty() == true) {
            registerMobileNoListener.value?.isMobileValid() == true
                    && registerCheckboxListener.value == true
        } else {
            registerMobileNoListener.value?.isMobileValid() == true
                    && registerReferralNoListener.value?.isReferIdValid() == true
                    && registerCheckboxListener.value == true
        }
    }

    fun register() {
        val request = RegisterRequest(registerMobileNoListener.value?.trim())
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            loginRegisterRepo.register(request)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiRegister.postValue(SingleEvent(it))

                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }

    fun login() {
        val request = LoginRequest(loginMobileNoListener.value)
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            loginRegisterRepo.login(request)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiLogin.postValue(SingleEvent(it))

                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }

    fun validateOTP(id: Int) {
        val request = OTPRequest(id, otpListener.value)
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            loginRegisterRepo.validateOTP(request)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiOTP.postValue(SingleEvent(it))

                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }
}