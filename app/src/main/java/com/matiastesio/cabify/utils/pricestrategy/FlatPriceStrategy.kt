package com.matiastesio.cabify.utils.pricestrategy

class FlatPriceStrategy : PriceStrategy {
    override fun calculatePrice(
        price: Double,
        discount: Double,
        quantity: Int,
        afterQty: Int
    ): Double =
        if (discount > 0 && quantity > 0 && quantity >= afterQty) {
            discount * quantity
        } else {
            if (price > 0 && quantity > 0) {
                price * quantity
            } else {
                0.0
            }
        }
}
