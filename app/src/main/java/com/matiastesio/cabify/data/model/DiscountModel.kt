package com.matiastesio.cabify.data.model

data class DiscountModel(
    val code: String?,
    val title: String?,
    val afterQty: Int?,
    val discount: Double?,
    val discountType: String?
)
