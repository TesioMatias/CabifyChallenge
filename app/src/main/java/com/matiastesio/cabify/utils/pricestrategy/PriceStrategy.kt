package com.matiastesio.cabify.utils.pricestrategy

interface PriceStrategy {
    fun calculatePrice(price: Double, discount: Double, quantity: Int, afterQty: Int): Double
}
