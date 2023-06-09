package com.matiastesio.cabify.data.mapper.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.model.DiscountModel

class DiscountMapperImpl : DiscountMapper {
    override fun mapEntityListToModelList(discountList: List<DiscountEntity>?): List<DiscountModel> =
        discountList?.map { entity ->
            DiscountModel(
                code = entity.code,
                title = entity.title,
                afterQty = entity.afterQty,
                discount = entity.discount,
                discountType = entity.discountType
            )
        } ?: run {
            mutableListOf()
        }

}
