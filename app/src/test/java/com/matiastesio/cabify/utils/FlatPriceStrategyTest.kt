package com.matiastesio.cabify.utils

import com.matiastesio.cabify.utils.pricestrategy.FlatPriceStrategy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class FlatPriceStrategyTest {

    lateinit var flatPriceStrategy: FlatPriceStrategy

    @Before
    fun onBefore() {
        flatPriceStrategy = FlatPriceStrategy()
    }

    @Test
    fun `given correct quantities, prices and the discount applies then return the correct calculated price`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 2

        // When
        val priceCalculated =
            flatPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(27.0, priceCalculated)
    }

    @Test
    fun `given wrong quanty and correct prices and the discount applies then return zero`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = -3
        val afterQty = 2

        // When
        val priceCalculated =
            flatPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given correct quanty, wrong prices and the discount applies then return the default price`() {
        // Given
        val price = 10.0
        val discountedPrice = -9.0
        val quantity = 3
        val afterQty = 2

        // When
        val priceCalculated =
            flatPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(30.0, priceCalculated)
    }

    @Test
    fun `given correct quanty and prices and the discount does not apply then return the default price`() {
        // Given
        val price = 10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 4

        // When
        val priceCalculated =
            flatPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(30.0, priceCalculated)
    }

    @Test
    fun `given correct quanty and wrong prices and the discount does not apply then return zero`() {
        // Given
        val price = -10.0
        val discountedPrice = 9.0
        val quantity = 3
        val afterQty = 4

        // When
        val priceCalculated =
            flatPriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }
}