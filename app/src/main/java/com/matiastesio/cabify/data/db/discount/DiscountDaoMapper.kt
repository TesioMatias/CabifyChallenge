package com.matiastesio.cabify.data.db.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.network.response.discount.DiscountItem
import com.matiastesio.cabify.data.network.response.discount.DiscountResponse

class DiscountDaoMapper : DaoDiscountMapper {
    override fun mapToDao(list: List<DiscountItem>?): List<DiscountEntity> =
        list?.map { it ->
            DiscountEntity(
                code = it.code,
                title = it.title,
                discount = it.discount,
                discountType = it.discountType,
                afterQty = it.afterQty
            )
        } ?: run {
            listOf()
        }

    override fun mapFromDao(allProducts: List<DiscountEntity>?): DiscountResponse =
        DiscountResponse(mapToList(allProducts))

    private fun mapToList(allProducts: List<DiscountEntity>?): List<DiscountItem> =
        allProducts?.map { it ->
            DiscountItem(
                code = it.code,
                title = it.title,
                discount = it.discount,
                discountType = it.discountType,
                afterQty = it.afterQty
            )
        } ?: run {
            listOf()
        }
}
