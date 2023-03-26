package com.matiastesio.cabify.data.repositories.product

import com.matiastesio.cabify.data.db.product.ProductDao
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.network.response.product.ProductResponse
import com.matiastesio.cabify.data.network.service.product.ProductService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductService,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun getProductsFromRemote(): ProductResponse? = api.getProducts()

    override suspend fun getProductsFromLocal(): List<ProductEntity> = productDao.getProducts()

    override suspend fun insertProducts(products: List<ProductEntity>) = productDao.insertAll(products)

    override suspend fun clearProducts() = productDao.deleteProducts()
}
