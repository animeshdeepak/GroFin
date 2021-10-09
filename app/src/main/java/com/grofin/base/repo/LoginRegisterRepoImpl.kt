package com.grofin.base.repo

import com.grofin.base.service.LoginRegisterService
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRegisterRepoImpl @Inject constructor(private val service: LoginRegisterService) :
    LoginRegisterRepo {
    override fun register(request: RegisterRequest): Single<RegisterResponse> =
        service.register(request)

    override fun login(request: LoginRequest): Single<LoginResponse> = service.login(request)
}