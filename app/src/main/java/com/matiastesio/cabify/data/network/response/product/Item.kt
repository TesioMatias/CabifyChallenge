package com.matiastesio.cabify.data.network.response.product

import com.google.gson.annotations.SerializedName
import com.matiastesio.cabify.utils.Model

//Con este @Model, podria sacarle los SerializedNames a todos los atributos :)
@Model
data class Item(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("icon")
    val icon: String
)
