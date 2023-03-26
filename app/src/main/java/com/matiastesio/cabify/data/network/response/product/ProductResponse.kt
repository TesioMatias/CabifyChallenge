package com.matiastesio.cabify.data.network.response.product

import com.google.gson.annotations.SerializedName
import com.matiastesio.cabify.utils.Model

//Con este @Model, podria sacarle los SerializedNames a todos los atributos :)
@Model
data class ProductResponse(
    @SerializedName("products")
    val products: List<Item>?
)
