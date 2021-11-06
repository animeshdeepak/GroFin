package com.grofin.base.repo

import com.grofin.feature.response.CategoriesResponse
import com.grofin.feature.response.SubCategoriesResponse
import io.reactivex.rxjava3.core.Single

interface HomeRepo {
    fun getCategories(): Single<CategoriesResponse>
    fun getSubCategories(categoryId: Int): Single<SubCategoriesResponse>
}