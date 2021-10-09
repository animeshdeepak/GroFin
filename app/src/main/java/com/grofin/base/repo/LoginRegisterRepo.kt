package com.grofin.base.repo

import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.OTPResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single

interface LoginRegisterRepo {
    fun register(request: RegisterRequest): Single<RegisterResponse>
    fun login(request: LoginRequest): Single<LoginResponse>
    fun validateOTP(request: OTPRequest): Single<OTPResponse>
}