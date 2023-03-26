package com.matiastesio.cabify.data.network.response.discount

import com.google.gson.annotations.SerializedName
import com.matiastesio.cabify.utils.Model

//Con este @Model, podria sacarle los SerializedNames a todos los atributos :)
@Model
data class DiscountItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("after_qty")
    val afterQty: Int,
    @SerializedName("discount")
    val discount: Double,
    @SerializedName("discount_type")
    val discountType: String
)
