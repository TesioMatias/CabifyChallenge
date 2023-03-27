package com.matiastesio.cabify.ui.cart

import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.data.model.CatalogItemModel

object CartDataSource {
    val getListNewItemsOne: List<CatalogItemModel> = getListNew()
    val getListOldItemsOne: List<CatalogItemModel> = getListOld()
    val getCartItems: List<CartItemModel> = getListOfCartItems()

    private fun getListOld(): List<CatalogItemModel> {
        val list: MutableList<CatalogItemModel> = mutableListOf()
        list.add(
            CatalogItemModel(
                code = "code",
                name = "name",
                price = 10.0,
                icon = "icon",
                discount = null
            )
        )
        return list
    }

    private fun getListNew(): List<CatalogItemModel> {
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

    private fun getListOfCartItems(): List<CartItemModel> {
        val list: MutableList<CartItemModel> = mutableListOf()
        list.add(
            CartItemModel(
                code = "code",
                name = "name",
                price = 10.0,
                icon = "icon",
                title = "title",
                afterQty = 1,
                discount = 0.4,
                discountType = "discountType",
                quantity = 1
            )
        )
        return list
    }
}