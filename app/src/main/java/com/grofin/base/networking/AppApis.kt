package com.grofin.base.networking

import com.grofin.feature.request.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface AppApis {
    @GET(AppEndPoints.REQ_RES_SINGLE_USER)
    fun getSingleUser(): Single<User>
}