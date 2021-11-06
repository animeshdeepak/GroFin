package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class UserResponse(
    @SerializedName("id")
    @Expose
    val id: Int = -1,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("email_verified_at")
    @Expose
    val emailVerifiedAt: String? = null,

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: String? = null,

    @SerializedName("phone")
    @Expose
    val phone: String? = null,
) : BaseResponse()
