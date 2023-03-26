package com.matiastesio.cabify.utils.pricestrategy

import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.PERCENTAGE_DISCOUNT_TYPE

object ItemPriceStrategy {

    private var strategy: PriceStrategy = DefaultPriceStrategy()

    fun setStrategy(discountType: String) {
        strategy = when (discountType) {
            FLAT_PRICE_DISCOUNT_TYPE -> FlatPriceStrategy()
            PERCENTAGE_DISCOUNT_TYPE -> PercentagePriceStrategy()
            else -> DefaultPriceStrategy()
        }
    }

    fun calculatePrice(price: Double, discount: Double, quantity: Int, afterQty: Int): Double {
        return strategy.calculatePrice(price, discount, quantity, afterQty)
    }
}
