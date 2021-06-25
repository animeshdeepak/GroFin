package com.grofin.base.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grofin.base.SharedPrefHelper
import com.grofin.base.exception.NoConnectivityException
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper

    private val compositeDisposable = CompositeDisposable()

    var _isLoading = MutableLiveData<SingleEvent<ApiStatus>>()
    val isLoading: LiveData<SingleEvent<ApiStatus>>
        get() = _isLoading

    private var _errorMessage = MediatorLiveData<SingleEvent<String>>()
    val errorMessage: LiveData<SingleEvent<String>>
        get() = _errorMessage

    protected fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

    override fun onCleared() = compositeDisposable.clear()
    
    protected fun setError(error: Throwable) = handleError(error)

    private fun handleError(error: Throwable) {
        when (error) {
            is HttpException ->
                when (error.code()) {
                    401 -> setFailedMessage("Your session has expired. Please login to continue.")
                    else ->
                        setFailedMessage("Something went wrong... try after sometime.")
                }
            is NoConnectivityException ->
                setFailedMessage(error.message)

            is IOException ->
                setFailedMessage("Something went wrong... try after sometime.")
            else -> error.message?.let { setFailedMessage(it) }
        }
    }

    private fun setFailedMessage(message: String) {
        _errorMessage.postValue(SingleEvent(message))
    }
}