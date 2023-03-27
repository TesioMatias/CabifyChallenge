package com.matiastesio.cabify.data.db.product

import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.network.response.product.Item
import com.matiastesio.cabify.data.network.response.product.ProductResponse

class ProductDaoMapper : DaoProductMapper {
    override fun mapToDao(list: List<Item>?): List<ProductEntity> =
        list?.map { it ->
            ProductEntity(
                code = it.code,
                name = it.name,
                price = it.price,
                icon = it.icon
            )
        } ?: run {
            mutableListOf()
        }

    override fun mapFromDao(allProducts: List<ProductEntity>?): ProductResponse =
        ProductResponse(mapToList(allProducts))

    private fun mapToList(allProducts: List<ProductEntity>?): List<Item> =
        allProducts?.map { it ->
            Item(
                code = it.code,
                name = it.name,
                price = it.price,
                icon = it.icon
            )
        } ?: run {
            listOf()
        }

}
