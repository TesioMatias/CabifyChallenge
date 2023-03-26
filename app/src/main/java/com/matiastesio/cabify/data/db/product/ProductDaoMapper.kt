package com.matiastesio.cabify.data.db.product

import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.network.response.product.Item
import com.matiastesio.cabify.data.network.response.product.ProductResponse

class ProductDaoMapper : DaoProductMapper {
    override fun mapToDao(list: List<Item>?): MutableList<ProductEntity> {
        val listOfEntities = mutableListOf<ProductEntity>()
        list?.forEach {
            listOfEntities.add(
                ProductEntity(
                    code = it.code,
                    name = it.name,
                    price = it.price,
                    icon = it.icon
                )
            )
        }
        return listOfEntities
    }

    override fun mapFromDao(allProducts: List<ProductEntity>?): ProductResponse =
        ProductResponse(mapToList(allProducts))

    private fun mapToList(allProducts: List<ProductEntity>?): List<Item> {
        val list = mutableListOf<Item>()
        allProducts?.forEach {
            Item(
                code = it.code,
                name = it.name,
                price = it.price,
                icon = it.icon
            )
        }
        return list
    }
}
