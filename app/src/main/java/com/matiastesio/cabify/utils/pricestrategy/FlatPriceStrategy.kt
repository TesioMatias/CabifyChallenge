package com.matiastesio.cabify.utils.pricestrategy

class FlatPriceStrategy : PriceStrategy {
    override fun calculatePrice(
        price: Double,
        discount: Double,
        quantity: Int,
        afterQty: Int
    ): Double =
        if (quantity >= afterQty) {
            discount * quantity
        } else {
            price * quantity
        }
}
