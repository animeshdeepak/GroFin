package com.grofin.base.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grofin.base.SharedPrefHelper
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    lateinit var sharedPrefHelper: SharedPrefHelper

    var _isLoading = MutableLiveData<SingleEvent<ApiStatus>>()
    val isLoading: LiveData<SingleEvent<ApiStatus>>
        get() = _isLoading
}