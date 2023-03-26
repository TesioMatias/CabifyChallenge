package com.matiastesio.cabify.utils

import com.matiastesio.cabify.utils.Constants.DEFAULT_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.PERCENTAGE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.pricestrategy.ItemPriceStrategy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ItemPriceStrategyTest {

    @Before
    fun onBefore() {
        ItemPriceStrategy.setStrategy(DEFAULT_DISCOUNT_TYPE)
    }

    @Test
    fun `given no strategy then calculate default price`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 2

        // When
        val priceCalculated =
            ItemPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(30.0, priceCalculated)
    }

    @Test
    fun `given wrong strategy then calculate default price`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 2

        // When
        ItemPriceStrategy.setStrategy("sldkf")
        val priceCalculated =
            ItemPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(30.0, priceCalculated)
    }

    @Test
    fun `given flat strategy then calculate flat price`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 2

        // When
        ItemPriceStrategy.setStrategy(FLAT_PRICE_DISCOUNT_TYPE)
        val priceCalculated =
            ItemPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(27.0, priceCalculated)
    }

    @Test
    fun `given percentage strategy then calculate percentage price`() {
        // Given
        val price = 10.0
        val discountedPrice = 0.5
        val quantity = 2
        val afterQty = 1

        // When
        ItemPriceStrategy.setStrategy(PERCENTAGE_DISCOUNT_TYPE)
        val priceCalculated =
            ItemPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(10.0, priceCalculated)
    }
}