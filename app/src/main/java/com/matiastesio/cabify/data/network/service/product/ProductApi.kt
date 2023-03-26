package com.matiastesio.cabify.data.network.service.product

import com.matiastesio.cabify.data.network.response.product.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>
}
