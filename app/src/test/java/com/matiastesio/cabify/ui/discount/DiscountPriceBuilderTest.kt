package com.matiastesio.cabify.ui.discount

import com.matiastesio.cabify.ui.discounts.list.builder.DiscountPriceBuilder
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.PERCENTAGE_DISCOUNT_TYPE
import org.junit.Test

class DiscountPriceBuilderTest {

    @Test
    fun `given inccorrect discount type then return empty text`() {
        // When
        val price = DiscountPriceBuilder
            .create()
            .withDiscountType("discountType")
            .withDiscountPrice(19.0)
            .build()

        // Then
        assert(price == "")
    }

    @Test
    fun `given flat price discount type then return corresponding text`() {
        // When
        val price = DiscountPriceBuilder
            .create()
            .withDiscountType(FLAT_PRICE_DISCOUNT_TYPE)
            .withDiscountPrice(19.0)
            .build()

        // Then
        assert(price == "19,00 new price!")
    }

    @Test
    fun `given percentage price discount type then return corresponding text`() {
        // When
        val price = DiscountPriceBuilder
            .create()
            .withDiscountType(PERCENTAGE_DISCOUNT_TYPE)
            .withDiscountPrice(0.5)
            .build()

        // Then
        assert(price == "50 % off!")
    }
}