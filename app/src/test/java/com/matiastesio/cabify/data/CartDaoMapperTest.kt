package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.cart.CartDaoMapper
import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import org.junit.Test

class CartDaoMapperTest {

    @Test
    fun `given a catalog item then map it to cart entity`() {
        // Given
        val itemModel = CatalogItemModel(
            code = "code",
            name = "name",
            price = 19.0,
            icon = "icon",
            discount = null
        )

        // When
        val mapped = CartDaoMapper().toEntity(itemModel)

        // Then
        assert(itemModel.code == mapped.code)
        assert(itemModel.name == mapped.name)
        assert(itemModel.price == mapped.price)
        assert(itemModel.icon == mapped.icon)
    }

    @Test
    fun `given a cart list then map it to catalog list`() {
        // Given
        val listCat: MutableList<CartEntity> = mutableListOf()
        listCat.add(
            CartEntity(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon",
                title = "title",
                afterQty = 3,
                discount = 18.0,
                discountType = "discountType",
                quantity = 2
            )
        )

        // When
        val mapped = CartDaoMapper().fromEntity(listCat)

        // Then
        assert(listCat.size == mapped.size)
    }

    @Test
    fun `given a cart list then attrs are map`() {
        // Given
        val listCat: MutableList<CartEntity> = mutableListOf()
        listCat.add(
            CartEntity(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon",
                title = "title",
                afterQty = 3,
                discount = 18.0,
                discountType = "discountType",
                quantity = 2
            )
        )

        // When
        val mapped = CartDaoMapper().fromEntity(listCat)

        // Then
        assert(listCat[0].code == mapped[0].code)
        assert(listCat[0].name == mapped[0].name)
        assert(listCat[0].price == mapped[0].price)
        assert(listCat[0].icon == mapped[0].icon)
        assert(listCat[0].title == mapped[0].title)
        assert(listCat[0].afterQty == mapped[0].afterQty)
        assert(listCat[0].discount == mapped[0].discount)
        assert(listCat[0].discountType == mapped[0].discountType)
        assert(listCat[0].quantity == mapped[0].quantity)
    }
}