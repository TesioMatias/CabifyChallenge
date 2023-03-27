package com.matiastesio.cabify.ui.catalog

import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.network.response.product.Item
import com.matiastesio.cabify.data.network.response.product.ProductResponse

object CatalogTestDataSource {
    val getResponse: ProductResponse = GetProductResponse()
    val getModel: CatalogItemModel = getModelItem()
    val getEntity: CatalogEntity = getEntityItem()
    val getListNewItemsOne: List<CatalogItemModel> = getListOfCatalogTwo()
    val getListOldItemsOne: List<CatalogItemModel> = getListOfCatalog()
    val getCatalogList: List<CatalogItemModel> = getListOfCatalog()
    val getProductList: List<ProductEntity> = getListOfProducts()
    val getDiscountList: List<DiscountEntity> = getListOfDiscounts()

    private fun GetProductResponse(): ProductResponse =
        ProductResponse(
            getItemList()
        )

    private fun getItemList(): List<Item> {
        val list: MutableList<Item> = mutableListOf()
        list.add(
            Item(
                code = "code",
                name = "name",
                price = 10.0,
                icon = "icon"
            )
        )
        return list
    }


    private fun getEntityItem(): CatalogEntity =
        CatalogEntity(
            code = "code",
            name = "name",
            price = 19.0,
            icon = "icon",
            title = "title",
            afterQty = 3,
            discount = 18.0,
            discountType = "discountType"
        )


    private fun getModelItem(): CatalogItemModel =
        CatalogItemModel(
            code = "code",
            name = "name",
            price = 10.0,
            icon = "icon",
            discount = null
        )

    private fun getListOfDiscounts(): List<DiscountEntity> {
        val list: MutableList<DiscountEntity> = mutableListOf()
        list.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 2,
                discount = 2.0,
                discountType = "discountType"
            )
        )
        return list
    }

    private fun getListOfProducts(): List<ProductEntity> {
        val list: MutableList<ProductEntity> = mutableListOf()
        list.add(
            ProductEntity(
                code = "code",
                name = "name",
                price = 10.0,
                icon = "icon"
            )
        )
        return list
    }

    private fun getListOfCatalog(): List<CatalogItemModel> {
        val list: MutableList<CatalogItemModel> = mutableListOf()
        list.add(getModelItem())
        return list
    }

    private fun getListOfCatalogTwo(): List<CatalogItemModel> {
        val list: MutableList<CatalogItemModel> = mutableListOf()
        list.add(
            CatalogItemModel(
                code = "code2",
                name = "name2",
                price = 10.0,
                icon = "icon2",
                discount = null
            )
        )
        return list
    }
}
