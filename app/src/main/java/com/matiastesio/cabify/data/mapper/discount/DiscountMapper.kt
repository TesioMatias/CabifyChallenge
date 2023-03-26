package com.matiastesio.cabify.data.mapper.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.model.DiscountModel

interface DiscountMapper {
    fun mapEntityListToModelList(
        discountList: List<DiscountEntity>?
    ): List<DiscountModel>
}
