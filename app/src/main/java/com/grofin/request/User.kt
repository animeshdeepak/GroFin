package com.grofin.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class User(
    @SerializedName("data")
    @Expose
    var data: Data? = null,

    @SerializedName("support")
    @Expose
    var support: Support? = null
) : BaseResponse()

data class Data(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null,

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null,

    @SerializedName("avatar")
    @Expose
    var avatar: String? = null
)

data class Support(
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("text")
    @Expose
    var text: String? = null
)