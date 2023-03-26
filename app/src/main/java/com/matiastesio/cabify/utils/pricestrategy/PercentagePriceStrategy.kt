package com.matiastesio.cabify.utils.pricestrategy

class PercentagePriceStrategy : PriceStrategy {
    override fun calculatePrice(
        price: Double,
        discount: Double,
        quantity: Int,
        afterQty: Int
    ): Double =
        if (price > 0 && discount > 0 && quantity > 0 && quantity >= afterQty) {
            price * discount * quantity
        } else {
            if (price > 0 && quantity > 0) {
                price * quantity
            } else {
                0.0
            }
        }
}
