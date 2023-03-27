package com.matiastesio.cabify.utils

import com.matiastesio.cabify.utils.pricestrategy.DefaultPriceStrategy
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultPriceStrategyTest {

    lateinit var defaultPriceStrategy: DefaultPriceStrategy

    @Before
    fun onBefore() {
        defaultPriceStrategy = DefaultPriceStrategy()
    }

    @Test
    fun `given correct quantities and prices then return the correct calculated price`() {
        // Given
        val price = 10.0
        val quantity = 2

        // When
        val priceCalculated = defaultPriceStrategy.calculatePrice(price, price, quantity, quantity)

        // Then
        assertEquals(20.0, priceCalculated)
    }

    @Test
    fun `given wrong quantity and correct price then return zero`() {
        // Given
        val price = 10.0
        val quantity = -2

        // When
        val priceCalculated = defaultPriceStrategy.calculatePrice(price, price, quantity, quantity)

        // Then
        assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given correct quantity and wrong price then return zero`() {
        // Given
        val price = -10.0
        val quantity = 2

        // When
        val priceCalculated = defaultPriceStrategy.calculatePrice(price, price, quantity, quantity)

        // Then
        assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given wrong quantity and price then return zero`() {
        // Given
        val price = -10.0
        val quantity = -2

        // When
        val priceCalculated = defaultPriceStrategy.calculatePrice(price, price, quantity, quantity)

        // Then
        assertEquals(0.0, priceCalculated)
    }
}