package com.matiastesio.cabify.data.repositories.product

import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.network.response.product.ProductResponse

interface ProductRepository {
    suspend fun getProductsFromRemote(): ProductResponse?
    suspend fun getProductsFromLocal(): List<ProductEntity>
    suspend fun insertProducts(products: List<ProductEntity>)
    suspend fun clearProducts()
}
