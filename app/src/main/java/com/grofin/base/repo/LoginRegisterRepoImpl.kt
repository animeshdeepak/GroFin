package com.grofin.base.repo

import com.grofin.base.service.LoginRegisterService
import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.ResendOTPRequest
import com.grofin.feature.response.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRegisterRepoImpl @Inject constructor(private val service: LoginRegisterService) : LoginRegisterRepo {
    override fun register(request: RegisterRequest): Single<RegisterResponse> = service.register(request)
    override fun login(request: LoginRequest): Single<LoginResponse> = service.login(request)
    override fun validateOTP(request: OTPRequest): Single<OTPResponse> = service.validateOTP(request)
    override fun getUser(): Single<UserResponse> = service.getUser()
    override fun resendOTP(request: ResendOTPRequest): Single<ResendOTPResponse> = service.resendOTP(request)
}