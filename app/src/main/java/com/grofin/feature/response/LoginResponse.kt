package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class LoginResponse(
    @SerializedName("data")
    @Expose
    val data: Data? = null
) : BaseResponse()
