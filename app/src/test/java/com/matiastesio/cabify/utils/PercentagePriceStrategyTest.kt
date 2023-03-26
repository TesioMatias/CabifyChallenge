package com.matiastesio.cabify.utils

import com.matiastesio.cabify.utils.pricestrategy.PercentagePriceStrategy
import io.mockk.MockKAnnotations
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class PercentagePriceStrategyTest {

    lateinit var percentagePriceStrategy: PercentagePriceStrategy

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        percentagePriceStrategy = PercentagePriceStrategy()
    }

    @Test
    fun `given correct quantities, prices and the discount applies then return the correct calculated price`() {
        // Given
        val price = 10.0
        val discountedPrice = 0.5
        val quantity = 2
        val afterQty = 1

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(10.0, priceCalculated)
    }

    @Test
    fun `given wrong price then return zero`() {
        // Given
        val price = -10.0
        val discountedPrice = 0.5
        val quantity = 2
        val afterQty = 1

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given wrong discounted price then return default price`() {
        // Given
        val price = 10.0
        val discountedPrice = -0.5
        val quantity = 2
        val afterQty = 1

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(20.0, priceCalculated)
    }

    @Test
    fun `given wrong quantity then return zero`() {
        // Given
        val price = 10.0
        val discountedPrice = 0.5
        val quantity = -2
        val afterQty = 1

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given discount does not apply then return default price`() {
        // Given
        val price = 10.0
        val discountedPrice = 0.5
        val quantity = 2
        val afterQty = 3

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(20.0, priceCalculated)
    }

    @Test
    fun `given discount does not apply and wrong price then return zero`() {
        // Given
        val price = -10.0
        val discountedPrice = 0.5
        val quantity = 2
        val afterQty = 3

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }

    @Test
    fun `given discount does not apply and wrong quantity then return zero`() {
        // Given
        val price = 10.0
        val discountedPrice = 0.5
        val quantity = -2
        val afterQty = 3

        // When
        val priceCalculated =
            percentagePriceStrategy.calculatePrice(price, discountedPrice, quantity, afterQty)

        // Then
        TestCase.assertEquals(0.0, priceCalculated)
    }
}