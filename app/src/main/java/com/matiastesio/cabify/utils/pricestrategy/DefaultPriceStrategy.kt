package com.matiastesio.cabify.utils.pricestrategy

class DefaultPriceStrategy : PriceStrategy {
    override fun calculatePrice(price: Double, discount: Double, quantity: Int, afterQty: Int): Double {
        val calculatedPrice = if (price > 0 && quantity > 0) {
            price * quantity
        } else {
            0.0
        }
        return calculatedPrice
    }
}
