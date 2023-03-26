package com.matiastesio.cabify.data.model

data class CatalogItemModel(
    val code: String,
    val name: String,
    val price: Double,
    val icon: String,
    val discount: DiscountModel?
)
