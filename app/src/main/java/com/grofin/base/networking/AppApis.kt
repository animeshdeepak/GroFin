package com.grofin.base.networking

import com.grofin.feature.request.LoginRequest
import com.grofin.feature.request.OTPRequest
import com.grofin.feature.request.RegisterRequest
import com.grofin.feature.request.ResendOTPRequest
import com.grofin.feature.response.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface AppApis {
    @POST(AppEndPoints.ENDPOINT_REGISTER)
    fun register(@Body request: RegisterRequest): Single<RegisterResponse>

    @POST(AppEndPoints.ENDPOINT_LOGIN)
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @POST(AppEndPoints.ENDPOINT_OTP)
    fun validateOTP(@Body request: OTPRequest): Single<OTPResponse>

    @GET(AppEndPoints.ENDPOINT_USER)
    fun getUser(@Header("Token") authorization: String?): Single<UserResponse>

    @POST(AppEndPoints.ENDPOINT_RESEND_OTP)
    fun resendOTP(@Body request: ResendOTPRequest): Single<ResendOTPResponse>

    @GET(AppEndPoints.ENDPOINT_CATEGORIES)
    fun getCategories(): Single<CategoriesResponse>

    @GET(AppEndPoints.ENDPOINT_SUB_CATEGORIES)
    fun getSubCategories(@Query("catagory_id") categoryId: Int): Single<SubCategoriesResponse>
}