package com.grofin.feature.dashboard.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.repo.HomeRepo
import com.grofin.feature.response.CategoriesResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ServiceViewModel @Inject constructor(private val homeRepo: HomeRepo) : BaseViewModel() {
    val isRVVisible = MutableLiveData<Boolean>()

    private var _apiCategories = MutableLiveData<SingleEvent<CategoriesResponse>>()
    val apiCategories: LiveData<SingleEvent<CategoriesResponse>>
        get() = _apiCategories


    fun getCategories() {
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            homeRepo.getCategories()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiCategories.postValue(SingleEvent(it))

                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }
}