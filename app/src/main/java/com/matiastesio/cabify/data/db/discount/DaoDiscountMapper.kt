package com.matiastesio.cabify.data.db.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.network.response.discount.DiscountItem
import com.matiastesio.cabify.data.network.response.discount.DiscountResponse

interface DaoDiscountMapper {
    fun mapToDao(list: List<DiscountItem>?): List<DiscountEntity>
    fun mapFromDao(allProducts: List<DiscountEntity>?): DiscountResponse
}