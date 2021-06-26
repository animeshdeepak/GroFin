package com.grofin.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.repo.SplashRepo
import com.grofin.request.User
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val splashRepo: SplashRepo) : BaseViewModel() {
    private var _apiUser = MutableLiveData<SingleEvent<User>>()
    val apiUser: LiveData<SingleEvent<User>>
        get() = _apiUser

    fun getSingleUser() {
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            splashRepo.getSingleUser()
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