package com.grofin.base.service

import com.grofin.base.networking.AppApis
import com.grofin.base.networking.qualifier.AuthUrlAPI
import com.grofin.feature.response.CategoriesResponse
import com.grofin.feature.response.SubCategoriesResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
class HomeService(@AuthUrlAPI private val authApi: AppApis) {
    fun getCategories(): Single<CategoriesResponse> = authApi.getCategories()
    fun getSubCategories(categoryId: Int): Single<SubCategoriesResponse> = authApi.getSubCategories(categoryId)
}