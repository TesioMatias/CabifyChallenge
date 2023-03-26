package com.matiastesio.cabify.ui.discounts.list.builder

interface DiscountTypeStep {
    fun withDiscountType(discountedType: String?): DiscountPriceStep
}
