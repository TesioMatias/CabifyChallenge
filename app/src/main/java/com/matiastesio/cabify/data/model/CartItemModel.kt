package com.matiastesio.cabify.data.model

data class CartItemModel(
    val code: String,
    val name: String,
    val price: Double,
    val icon: String,
    val title: String,
    val afterQty: Int,
    val discount: Double,
    val discountType: String,
    val quantity: Int
)


