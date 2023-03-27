package com.matiastesio.cabify.ui.discount

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.data.network.response.discount.DiscountItem
import com.matiastesio.cabify.data.network.response.discount.DiscountResponse

object DiscountDataSource {
    val getResponseList: List<DiscountItem> = getDiscountItemList()
    val getDiscountResponse: DiscountResponse = getDiscountResponseItem()
    val getListNewItemsOne: List<DiscountModel> = getList()
    val getListOldItemsOne: List<DiscountModel> = getOldList()
    val getModelList: List<DiscountModel> = getList()
    val getDiscounts: List<DiscountEntity> = getEntityList()

    private fun getDiscountResponseItem(): DiscountResponse =
        DiscountResponse(
            getDiscountItemList()
        )

    private fun getDiscountItemList(): List<DiscountItem> {
        val list: MutableList<DiscountItem> = mutableListOf()
        list.add(
            DiscountItem(
                code = "code2",
                title = "title2",
                afterQty = 3,
                discount = 19.0,
                discountType = "discountType2"
            )
        )
        return list
    }

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