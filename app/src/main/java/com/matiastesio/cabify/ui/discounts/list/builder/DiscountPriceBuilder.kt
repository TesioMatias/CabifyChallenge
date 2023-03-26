package com.matiastesio.cabify.ui.discounts.list.builder

import com.matiastesio.cabify.utils.Constants.DEFAULT_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TEXT
import com.matiastesio.cabify.utils.Constants.FLAT_PRICE_DISCOUNT_TYPE
import com.matiastesio.cabify.utils.Constants.PERCENTAGE_DISCOUNT_TEXT
import com.matiastesio.cabify.utils.Constants.PERCENTAGE_DISCOUNT_TYPE

object DiscountPriceBuilder {

    fun create(): DiscountTypeStep {
        return Builder()
    }

    class Builder : DiscountTypeStep, DiscountPriceStep, DiscountBuildStep {
        private lateinit var _discountType: String
        private var _discountPrice: Double = 0.0

        override fun withDiscountType(discountedType: String?): DiscountPriceStep {
            discountedType?.let {
                _discountType = discountedType
            } ?: run {
                _discountType = DEFAULT_DISCOUNT_TYPE
            }
            return this
        }

        override fun withDiscountPrice(price: Double): DiscountBuildStep {
            _discountPrice = price
            return this
        }

        // could have used an enum to avoid the else branch
        override fun build(): String {
            return when (_discountType) {
                FLAT_PRICE_DISCOUNT_TYPE -> getFlatPriceText(_discountPrice)
                PERCENTAGE_DISCOUNT_TYPE -> getPercentageText(_discountPrice)
                else -> ""
            }
        }

        private fun getPercentageText(_discountPrice: Double): String =
            String.format(PERCENTAGE_DISCOUNT_TEXT, _discountPrice * 100)

        private fun getFlatPriceText(_discountPrice: Double): String =
            String.format(FLAT_PRICE_DISCOUNT_TEXT, _discountPrice)
    }
}