package com.grofin.base.service

import com.grofin.base.networking.AppApis
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.OTPResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
class LoginRegisterService(private val api: AppApis) {
    fun register(request: RegisterRequest): Single<RegisterResponse> = api.register(request)
    fun login(request: LoginRequest): Single<LoginResponse> = api.login(request)
    fun validateOTP(request: OTPRequest): Single<OTPResponse> = api.validateOTP(request)
}