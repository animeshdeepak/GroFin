package com.grofin.base.repo

import com.grofin.base.service.SplashService
import com.grofin.feature.request.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(private val splashService: SplashService) : SplashRepo {
    override fun getSingleUser(): Single<User> {
        return splashService.getSingleUser()
    }
}