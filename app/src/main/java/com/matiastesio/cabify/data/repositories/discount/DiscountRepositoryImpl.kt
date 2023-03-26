package com.matiastesio.cabify.data.repositories.discount

import com.matiastesio.cabify.data.db.discount.DiscountDao
import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.network.response.discount.DiscountResponse
import com.matiastesio.cabify.data.network.service.discount.DiscountService
import javax.inject.Inject

class DiscountRepositoryImpl @Inject constructor(
    private val api: DiscountService,
    private val discountDao: DiscountDao
) : DiscountRepository {

    override suspend fun getDiscountsFromRemote(): DiscountResponse? = api.getDiscounts()

    override suspend fun getDiscountsFromLocal(): List<DiscountEntity> = discountDao.getDiscounts()

    override suspend fun insertDiscounts(discounts: List<DiscountEntity>) =
        discountDao.insertDiscounts(discounts)

    override suspend fun clearDiscounts() = discountDao.deleteDiscounts()
}
