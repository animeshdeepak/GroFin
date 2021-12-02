package com.grofin.base.service

import com.grofin.base.networking.AppApis
import com.grofin.feature.response.CategoriesResponse
import com.grofin.feature.response.SubCategoriesResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
class HomeService(private val api: AppApis) {
    fun getCategories(): Single<CategoriesResponse> = api.getCategories()
    fun getSubCategories(categoryId: Int): Single<SubCategoriesResponse> =
        api.getSubCategories(categoryId)
}