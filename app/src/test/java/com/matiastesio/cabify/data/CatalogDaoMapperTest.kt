package com.matiastesio.cabify.data

import com.matiastesio.cabify.data.db.catalog.CatalogDaoMapper
import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import org.junit.Test

class CatalogDaoMapperTest {

    @Test
    fun `given a catalog list then map it to catalog model`() {
        // Given
        val listCat: MutableList<CatalogItemModel> = mutableListOf()
        listCat.add(
            CatalogItemModel(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon",
                discount = null
            )
        )

        // When
        val mapped = CatalogDaoMapper().toEntity(listCat)

        // Then
        assert(listCat.size == mapped.size)
    }

    @Test
    fun `given a catalog list then attrs are map`() {
        // Given
        val listCat: MutableList<CatalogItemModel> = mutableListOf()
        listCat.add(
            CatalogItemModel(
                code = "code",
                name = "name",
                price = 19.0,
                icon = "icon",
                discount = null
            )
        )

        // When
        val mapped = CatalogDaoMapper().toEntity(listCat)

        // Then
        assert(listCat[0].code == mapped[0].code)
        assert(listCat[0].name == mapped[0].name)
        assert(listCat[0].price == mapped[0].price)
        assert(listCat[0].icon == mapped[0].icon)
    }

    @Test
    fun `given a catalog entity then map to model`() {
        // Given
        val entity = CatalogEntity(
            code = "code",
            name = "name",
            price = 19.0,
            icon = "icon",
            title = "title",
            afterQty = 3,
            discount = 18.0,
            discountType = "discountType"
        )

        // When
        val mapped = CatalogDaoMapper().fromEntity(entity)

        // Then
        assert(entity.code == mapped.code)
        assert(entity.name == mapped.name)
        assert(entity.price == mapped.price)
        assert(entity.icon == mapped.icon)
        assert(entity.title == mapped.discount?.title)
        assert(entity.afterQty == mapped.discount?.afterQty)
        assert(entity.discount == mapped.discount?.discount)
        assert(entity.discountType == mapped.discount?.discountType)
    }
}