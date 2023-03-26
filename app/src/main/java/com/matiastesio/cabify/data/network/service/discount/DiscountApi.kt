package com.matiastesio.cabify.data.network.service.discount

import com.matiastesio.cabify.data.network.response.discount.DiscountResponse
import retrofit2.Response
import retrofit2.http.GET

interface DiscountApi {

    @GET("discounts")
    suspend fun getDiscounts(): Response<DiscountResponse>
}