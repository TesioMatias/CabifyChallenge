package com.matiastesio.cabify.data.network.response.discount

import com.google.gson.annotations.SerializedName
import com.matiastesio.cabify.utils.Model

//Con este @Model, podria sacarle los SerializedNames a todos los atributos :)
@Model
data class DiscountResponse(
    @SerializedName("discounts")
    val discounts: List<DiscountItem>?
)
