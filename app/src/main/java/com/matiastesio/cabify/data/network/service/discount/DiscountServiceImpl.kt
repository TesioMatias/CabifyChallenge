package com.matiastesio.cabify.data.network.service.discount

import com.matiastesio.cabify.data.network.response.discount.DiscountResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscountServiceImpl @Inject constructor(private val api: DiscountApi) : DiscountService {

    override suspend fun getDiscounts(): DiscountResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getDiscounts()
            response.body()
        }
    }
}