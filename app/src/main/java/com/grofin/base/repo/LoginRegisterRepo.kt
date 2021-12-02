package com.grofin.base.repo

import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.ResendOTPRequest
import com.grofin.feature.response.*
import io.reactivex.rxjava3.core.Single

interface LoginRegisterRepo {
    fun register(request: RegisterRequest): Single<RegisterResponse>
    fun login(request: LoginRequest): Single<LoginResponse>
    fun validateOTP(request: OTPRequest): Single<OTPResponse>
    fun getUser(): Single<UserResponse>
    fun resendOTP(request: ResendOTPRequest): Single<ResendOTPResponse>
}