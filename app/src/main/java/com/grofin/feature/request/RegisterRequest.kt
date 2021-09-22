package com.grofin.feature.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseRequest

data class RegisterRequest(
    @SerializedName("phone")
    @Expose
    val phone: String? = null
) : BaseRequest()