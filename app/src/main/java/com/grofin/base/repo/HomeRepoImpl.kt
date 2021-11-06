package com.grofin.base.repo

import com.grofin.base.service.HomeService
import com.grofin.feature.response.CategoriesResponse
import com.grofin.feature.response.SubCategoriesResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(private val service: HomeService) : HomeRepo {
    override fun getCategories(): Single<CategoriesResponse> = service.getCategories()
    override fun getSubCategories(categoryId: Int): Single<SubCategoriesResponse> = service.getSubCategories(categoryId)
}