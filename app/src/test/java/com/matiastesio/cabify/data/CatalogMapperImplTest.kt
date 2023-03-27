package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.mapper.catalog.CatalogMapperImpl
import org.junit.Test

class CatalogMapperImplTest {

    @Test
    fun `given a product and discount list then map it to catalog model`() {
        // Given
        val listDisc: MutableList<DiscountEntity> = mutableListOf()
        listDisc.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )
        val listProd: MutableList<ProductEntity> = mutableListOf()
        listProd.add(
            ProductEntity(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = CatalogMapperImpl().mapResponseToModel(listProd, listDisc)

        // Then
        assert(listProd.size == mapped.size)
    }

    @Test
    fun `given a product and discount list then attr are mapped`() {
        // Given
        val listDisc: MutableList<DiscountEntity> = mutableListOf()
        listDisc.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )
        val listProd: MutableList<ProductEntity> = mutableListOf()
        listProd.add(
            ProductEntity(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = CatalogMapperImpl().mapResponseToModel(listProd, listDisc)

        // Then
        assert(listProd[0].code == mapped[0].code)
        assert(listProd[0].name == mapped[0].name)
        assert(listProd[0].price == mapped[0].price)
        assert(listProd[0].icon == mapped[0].icon)
        assert(listDisc[0].code == mapped[0].discount?.code)
        assert(listDisc[0].title == mapped[0].discount?.title)
        assert(listDisc[0].afterQty == mapped[0].discount?.afterQty)
        assert(listDisc[0].discount == mapped[0].discount?.discount)
        assert(listDisc[0].discountType == mapped[0].discount?.discountType)
    }
}