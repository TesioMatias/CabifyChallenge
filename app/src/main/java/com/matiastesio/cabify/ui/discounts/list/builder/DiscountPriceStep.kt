package com.matiastesio.cabify.ui.discounts.list.builder

interface DiscountPriceStep {
    fun withDiscountPrice(price: Double): DiscountBuildStep
}
