package com.grofin.base.service

import com.grofin.base.networking.AppApis
import com.grofin.base.networking.qualifier.AuthUrlAPI
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.ResendOTPRequest
import com.grofin.feature.response.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
class LoginRegisterService(private val api: AppApis, @AuthUrlAPI private val authApi: AppApis) {
    fun register(request: RegisterRequest): Single<RegisterResponse> = api.register(request)
    fun login(request: LoginRequest): Single<LoginResponse> = api.login(request)
    fun validateOTP(request: OTPRequest): Single<OTPResponse> = api.validateOTP(request)
    fun getUser(): Single<UserResponse> = authApi.getUser()
    fun resendOTP(request: ResendOTPRequest): Single<ResendOTPResponse> = api.resendOTP(request)
}