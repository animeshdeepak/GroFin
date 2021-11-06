package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * login/register response/resend otp
 */
data class Data(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("otp")
    @Expose
    val otp: String
)
