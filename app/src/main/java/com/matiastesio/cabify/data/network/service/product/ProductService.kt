package com.matiastesio.cabify.data.network.service.product

import com.matiastesio.cabify.data.network.response.product.ProductResponse

interface ProductService {
    suspend fun getProducts(): ProductResponse?
}