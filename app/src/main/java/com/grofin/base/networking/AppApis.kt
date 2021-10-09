package com.grofin.base.networking

import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.User
import com.grofin.feature.response.LoginResponse
import com.grofin.feature.response.OTPResponse
import com.grofin.feature.response.RegisterResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface AppApis {
    @GET(AppEndPoints.REQ_RES_SINGLE_USER)
    fun getSingleUser(): Single<User>

    @POST(AppEndPoints.ENDPOINT_REGISTER)
    fun register(@Body request: RegisterRequest): Single<RegisterResponse>

    @POST(AppEndPoints.ENDPOINT_LOGIN)
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @POST(AppEndPoints.ENDPOINT_OTP)
    fun validateOTP(@Body request: OTPRequest): Single<OTPResponse>
}