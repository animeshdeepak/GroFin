package com.grofin.base.repo

import com.grofin.base.service.LoginRegisterService
import com.grofin.base.service.SplashService
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.User
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRegisterRepoImpl @Inject constructor(private val service: LoginRegisterService) : LoginRegisterRepo {
    override fun register(request: RegisterRequest): Single<RegisterResponse> = service.register(request)
}