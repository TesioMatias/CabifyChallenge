package com.matiastesio.cabify.utils.pricestrategy

class PercentagePriceStrategy : PriceStrategy {
    override fun calculatePrice(
        price: Double,
        discount: Double,
        quantity: Int,
        afterQty: Int
    ): Double =
        if (quantity >= afterQty) {
            price * discount * quantity
        } else {
            price * quantity
        }

}
