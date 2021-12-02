package com.grofin.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.grofin.base.base.BaseViewModel
import com.grofin.base.extensions.ApiStatus
import com.grofin.base.extensions.SingleEvent
import com.grofin.base.repo.HomeRepo
import com.grofin.feature.response.SubCategoriesResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepo: HomeRepo) : BaseViewModel() {
    private var _apiSubCategories = MutableLiveData<SingleEvent<SubCategoriesResponse>>()
    val apiSubCategories: LiveData<SingleEvent<SubCategoriesResponse>>
        get() = _apiSubCategories

    fun getSubCategories(categoryId: Int?) {
        _isLoading.postValue(SingleEvent(ApiStatus.LOADING))
        addDisposable(
            homeRepo.getSubCategories(categoryId ?: -1)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(SingleEvent(ApiStatus.SUCCESS))
                    _apiSubCategories.postValue(SingleEvent(it))

                }, {
                    _isLoading.postValue(SingleEvent(ApiStatus.FAILURE))
                    setError(it)
                })
        )
    }
}