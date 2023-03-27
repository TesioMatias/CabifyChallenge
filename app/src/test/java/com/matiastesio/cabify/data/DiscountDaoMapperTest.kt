package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.discount.DiscountDaoMapper
import com.matiastesio.cabify.data.network.response.discount.DiscountItem
import org.junit.Test

class DiscountDaoMapperTest {

    @Test
    fun `given a product list then map to entities`() {
        // Given
        val listDisc: MutableList<DiscountItem> = mutableListOf()
        listDisc.add(
            DiscountItem(
                code = "code",
                title = "title",
                discount = 19.0,
                discountType = "discountType",
                afterQty = 3
            )
        )

        // When
        val mapped = DiscountDaoMapper().mapToDao(listDisc)

        // Then
        assert(listDisc.size == mapped.size)
    }

    @Test
    fun `given a product list then attrs are map`() {
        // Given
        val listDisc: MutableList<DiscountItem> = mutableListOf()
        listDisc.add(
            DiscountItem(
                code = "code",
                title = "title",
                discount = 19.0,
                discountType = "discountType",
                afterQty = 3
            )
        )

        // When
        val mapped = DiscountDaoMapper().mapToDao(listDisc)

        // Then
        assert(listDisc[0].code == mapped[0].code)
        assert(listDisc[0].title == mapped[0].title)
        assert(listDisc[0].discount == mapped[0].discount)
        assert(listDisc[0].discountType == mapped[0].discountType)
        assert(listDisc[0].afterQty == mapped[0].afterQty)
    }

    @Test
    fun `given a product entity list then map to model`() {
        // Given
        val listDisc: MutableList<DiscountItem> = mutableListOf()
        listDisc.add(
            DiscountItem(
                code = "code",
                title = "title",
                discount = 19.0,
                discountType = "discountType",
                afterQty = 3
            )
        )

        // When
        val mapped = DiscountDaoMapper().mapToDao(listDisc)
        val mappedModel = DiscountDaoMapper().mapFromDao(mapped)

        // Then
        assert(listDisc.size == mappedModel.discounts?.size)
    }

    @Test
    fun `given a product entity list then attrs are map`() {
        // Given
        val listDisc: MutableList<DiscountItem> = mutableListOf()
        listDisc.add(
            DiscountItem(
                code = "code",
                title = "title",
                discount = 19.0,
                discountType = "discountType",
                afterQty = 3
            )
        )

        // When
        val mapped = DiscountDaoMapper().mapToDao(listDisc)
        val mappedModel = DiscountDaoMapper().mapFromDao(mapped)

        // Then
        assert(listDisc[0].code == mappedModel.discounts?.get(0)?.code)
        assert(listDisc[0].title == mappedModel.discounts?.get(0)?.title)
        assert(listDisc[0].discount == mappedModel.discounts?.get(0)?.discount)
        assert(listDisc[0].discountType == mappedModel.discounts?.get(0)?.discountType)
        assert(listDisc[0].afterQty == mappedModel.discounts?.get(0)?.afterQty)
    }
}