package com.matiastesio.cabify.data.repositories.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.network.response.discount.DiscountResponse

interface DiscountRepository {
    suspend fun getDiscountsFromRemote(): DiscountResponse?
    suspend fun getDiscountsFromLocal(): List<DiscountEntity>
    suspend fun insertDiscounts(discounts: List<DiscountEntity>)
    suspend fun clearDiscounts()
}
