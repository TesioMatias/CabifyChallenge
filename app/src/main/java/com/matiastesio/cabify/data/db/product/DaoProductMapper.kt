package com.matiastesio.cabify.data.db.product

import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.network.response.product.Item
import com.matiastesio.cabify.data.network.response.product.ProductResponse

interface DaoProductMapper {
    fun mapToDao(list: List<Item>?): List<ProductEntity>
    fun mapFromDao(allProducts: List<ProductEntity>?): ProductResponse
}
