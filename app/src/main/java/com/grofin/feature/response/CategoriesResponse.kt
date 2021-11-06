package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class CategoriesResponse(
    @SerializedName("data")
    @Expose
    var data: CategoriesData,
) : BaseResponse()

data class CategoriesData(
    @SerializedName("catagories")
    @Expose
    var categoriesList: ArrayList<Category>? = null,
)

data class Category(
    @SerializedName("id")
    @Expose
    var id: Int? = -1,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null,
    @SerializedName("resource_url")
    @Expose
    var resourceUrl: String? = null,
)