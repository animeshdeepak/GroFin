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
import com.grofin.feature.request.ResendOTPRequest
import com.grofin.feature.response.*
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

    private var _apiResendOTP = MutableLiveData<SingleEvent<ResendOTPResponse>>()
    val apiResendOTP: LiveData<SingleEvent<ResendOTPResponse>>
        get() = _apiResendOTP

    private var _apiUser = MutableLiveData<SingleEvent<UserResponse>>()
    val apiUser: LiveData<SingleEvent<UserResponse>>
        get() = _apiUser

    fun registerDataValidation(): Boolean {
        return if (
            registerReferralNoListener.value == null ||
            registerReferralNoListener.value?.isReferIdValid() == true ||
            registerReferralNoListener.value?.isBlank() == true ||
            registerReferralNoListener.value?.isEmpty() == true
        ) {
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
                    it.data?.token?.let { token ->
                        sharedPrefHelper.saveToken(token)
                    }
                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }

    fun resendOTP(id: Int) {
        val request = ResendOTPRequest(id)
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            loginRegisterRepo.resendOTP(request)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiResendOTP.postValue(SingleEvent(it))
                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }

    fun getUser() {
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            loginRegisterRepo.getUser()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiUser.postValue(SingleEvent(it))
                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }
}