package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.product.ProductDaoMapper
import com.matiastesio.cabify.data.network.response.product.Item
import org.junit.Test

class ProductDaoMapperTest {

    @Test
    fun `given a product list then map to entities`() {
        // Given
        val listProd: MutableList<Item> = mutableListOf()
        listProd.add(
            Item(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = ProductDaoMapper().mapToDao(listProd)

        // Then
        assert(listProd.size == mapped.size)
    }

    @Test
    fun `given a product list then attrs are map`() {
        // Given
        val listProd: MutableList<Item> = mutableListOf()
        listProd.add(
            Item(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = ProductDaoMapper().mapToDao(listProd)

        // Then
        assert(listProd[0].code == mapped[0].code)
        assert(listProd[0].name == mapped[0].name)
        assert(listProd[0].price == mapped[0].price)
        assert(listProd[0].icon == mapped[0].icon)
    }

    @Test
    fun `given a product entity list then map to model`() {
        // Given
        val listProd: MutableList<Item> = mutableListOf()
        listProd.add(
            Item(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = ProductDaoMapper().mapToDao(listProd)
        val mappedModel = ProductDaoMapper().mapFromDao(mapped)

        // Then
        assert(listProd.size == mappedModel.products?.size)
    }

    @Test
    fun `given a product entity list then attrs are map`() {
        // Given
        val listProd: MutableList<Item> = mutableListOf()
        listProd.add(
            Item(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon"
            )
        )

        // When
        val mapped = ProductDaoMapper().mapToDao(listProd)
        val mappedModel = ProductDaoMapper().mapFromDao(mapped)

        // Then
        assert(listProd[0].code == mappedModel.products?.get(0)?.code)
        assert(listProd[0].name == mappedModel.products?.get(0)?.name)
        assert(listProd[0].price == mappedModel.products?.get(0)?.price)
        assert(listProd[0].icon == mappedModel.products?.get(0)?.icon)
    }
}