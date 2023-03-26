package com.matiastesio.cabify.data.network.service.discount

import com.matiastesio.cabify.data.network.response.discount.DiscountResponse

interface DiscountService {
    suspend fun getDiscounts(): DiscountResponse?
}