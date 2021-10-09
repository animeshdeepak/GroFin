package com.grofin.feature.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseRequest

data class OTPRequest(
    @SerializedName("id")
    @Expose
    val id: Int = -1,

    @SerializedName("otp")
    @Expose
    val otp: String? = null
) : BaseRequest()
