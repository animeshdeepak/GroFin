package com.grofin.base.service

import com.grofin.base.networking.AppApis
import com.grofin.request.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
class SplashService(private val api: AppApis) {
    fun getSingleUser(): Single<User> {
        return api.getSingleUser()
    }
}