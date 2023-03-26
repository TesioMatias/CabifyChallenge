package com.matiastesio.cabify.data.network.service.product

import com.matiastesio.cabify.data.network.response.product.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductServiceImpl @Inject constructor(private val api: ProductApi) : ProductService {

    override suspend fun getProducts(): ProductResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getAllProducts()
            response.body()
        }
    }
}
