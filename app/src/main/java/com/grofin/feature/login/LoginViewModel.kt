package com.grofin.feature.login

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.repo.LoginRegisterRepo
import com.grofin.feature.request.User
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import com.grofin.feature.response.RegisterResponse
import com.grofin.feature.request.RegisterRequest

class LoginViewModel @Inject constructor(private val loginRegisterRepo: LoginRegisterRepo) : BaseViewModel() {
    // for login screen
    var errorMobileVisibility = ObservableBoolean()
    // for register screen
    var errorMobileVisibilityRegister = ObservableBoolean()
    var errorReferralVisibility = ObservableBoolean()

    private var _apiRegister = MutableLiveData<SingleEvent<RegisterResponse>>()
    val apiRegister: LiveData<SingleEvent<RegisterResponse>>
        get() = _apiRegister

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
}