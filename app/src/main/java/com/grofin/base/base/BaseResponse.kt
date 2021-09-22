package com.grofin.base.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("success")
    @Expose
    val success: Boolean = false

    @SerializedName("message")
    @Expose
    val message: String? = null
}
