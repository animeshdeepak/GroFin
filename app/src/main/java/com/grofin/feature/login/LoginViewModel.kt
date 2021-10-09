package com.grofin.feature.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.repo.LoginRegisterRepo
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRegisterRepo: LoginRegisterRepo) :
    BaseViewModel() {
    // for login screen
    var errorMobileVisibility = ObservableBoolean()
    val enableNextBtn = MutableLiveData<String>()

    // for register screen
    var errorMobileVisibilityRegister = ObservableBoolean()
    var errorReferralVisibility = ObservableBoolean()

    private var _apiRegister = MutableLiveData<SingleEvent<RegisterResponse>>()
    val apiRegister: LiveData<SingleEvent<RegisterResponse>>
        get() = _apiRegister

    private var _apiLogin = MutableLiveData<SingleEvent<LoginResponse>>()
    val apiLogin: LiveData<SingleEvent<LoginResponse>>
        get() = _apiLogin

    fun register(phone: String?) {
        val request = RegisterRequest(phone)
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

    fun login(phone: String?) {
        val request = LoginRequest(phone)
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
}