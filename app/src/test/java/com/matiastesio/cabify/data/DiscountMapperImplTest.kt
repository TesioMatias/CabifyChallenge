package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.mapper.discount.DiscountMapperImpl
import org.junit.Test

class DiscountMapperImplTest {

    @Test
    fun `given a discount list then map it to model`() {
        // Given
        val list: MutableList<DiscountEntity> = mutableListOf()
        list.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )

        // When
        val mapped = DiscountMapperImpl().mapEntityListToModelList(list)

        // Then
        assert(list.size == mapped.size)
    }

    @Test
    fun `given a discount list then attr are mapped`() {
        // Given
        val list: MutableList<DiscountEntity> = mutableListOf()
        list.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )

        // When
        val mapped = DiscountMapperImpl().mapEntityListToModelList(list)

        // Then
        assert(list[0].code == mapped[0].code)
        assert(list[0].title == mapped[0].title)
        assert(list[0].afterQty == mapped[0].afterQty)
        assert(list[0].discount == mapped[0].discount)
        assert(list[0].discountType == mapped[0].discountType)
    }
}