package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class RegisterResponse(
    @SerializedName("data")
    @Expose
    val data: Data? = null
): BaseResponse()

data class Data(
    @SerializedName("id")
    @Expose
    val id : Int,
    @SerializedName("otp")
    @Expose
    val otp: String
)
