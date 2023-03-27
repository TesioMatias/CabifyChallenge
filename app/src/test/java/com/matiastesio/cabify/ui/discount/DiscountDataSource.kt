package com.matiastesio.cabify.ui.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.model.DiscountModel

object DiscountDataSource {
    val getListNewItemsOne: List<DiscountModel> = getList()
    val getListOldItemsOne: List<DiscountModel> = getOldList()
    val getModelList: List<DiscountModel> = getList()
    val getDiscounts: List<DiscountEntity> = getEntityList()

    private fun getOldList(): List<DiscountModel> {
        val list: MutableList<DiscountModel> = mutableListOf()
        list.add(
            DiscountModel(
                code = "code2",
                title = "title2",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType2"
            )
        )
        return list
    }

    private fun getList(): List<DiscountModel> {
        val list: MutableList<DiscountModel> = mutableListOf()
        list.add(
            DiscountModel(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )
        return list
    }

    private fun getEntityList(): List<DiscountEntity> {
        val list: MutableList<DiscountEntity> = mutableListOf()
        list.add(
            DiscountEntity(
                code = "code",
                title = "title",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType"
            )
        )
        return list
    }
}