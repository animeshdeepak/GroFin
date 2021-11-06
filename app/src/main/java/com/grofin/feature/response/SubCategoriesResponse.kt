package com.grofin.feature.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.grofin.base.base.BaseResponse

data class SubCategoriesResponse(
    @SerializedName("data")
    @Expose
    var data: SubCategoriesData,
) : BaseResponse()

data class SubCategoriesData(
    @SerializedName("catagories")
    @Expose
    var categoriesList: ArrayList<SubCategory>? = null,
)

data class SubCategory(
    @SerializedName("id")
    @Expose
    var id: Int? = -1,
    @SerializedName("image")
    @Expose
    var image: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("catagory_id")
    @Expose
    var categoryId: Int? = -1,
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