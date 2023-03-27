package com.matiastesio.cabify.data.db.catalog

import com.matiastesio.cabify.data.db.catalog.entity.CatalogEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.model.DiscountModel

class CatalogDaoMapper : DaoCatalogMapper {

    override fun toEntity(list: List<CatalogItemModel>?): List<CatalogEntity> =
        list?.map { item ->
            CatalogEntity(
                code = item.code,
                name = item.name,
                price = item.price,
                icon = item.icon,
                title = item.discount?.title ?: "",
                afterQty = item.discount?.afterQty ?: -1,
                discount = item.discount?.discount ?: -1.0,
                discountType = item.discount?.discountType ?: ""
            )
        } ?: run {
            listOf()
        }

    override fun fromEntity(entity: CatalogEntity): CatalogItemModel {
        return CatalogItemModel(
            code = entity.code,
            name = entity.name,
            price = entity.price,
            icon = entity.icon,
            discount = mapDiscount(
                entity.code,
                entity.title,
                entity.afterQty,
                entity.discount,
                entity.discountType
            )
        )
    }

    private fun mapDiscount(
        code: String,
        title: String,
        afterQty: Int,
        discount: Double,
        discountType: String
    ): DiscountModel? =
        if (
            shouldAddDiscount(
                code = code,
                title = title,
                afterQty = afterQty,
                discount = discount,
                discountType = discountType
            )
        ) {
            DiscountModel(
                code = code,
                title = title,
                afterQty = afterQty,
                discount = discount,
                discountType = discountType
            )
        } else {
            null
        }


    private fun addDiscountModel(
        title: String?,
        afterQty: Int?,
        discount: Double?,
        discountType: String?,
        code: String?
    ): DiscountModel? =
        if (
            shouldAddDiscount(
                code = code,
                title = title,
                afterQty = afterQty,
                discount = discount,
                discountType = discountType
            )
        ) {
            DiscountModel(
                code = code,
                title = title,
                afterQty = afterQty,
                discount = discount,
                discountType = discountType
            )
        } else {
            null
        }

    private fun shouldAddDiscount(
        title: String?,
        afterQty: Int?,
        discount: Double?,
        discountType: String?,
        code: String?
    ): Boolean =
        !title.isNullOrBlank() && afterQty != -1 && discount != -1.0 && !discountType.isNullOrBlank() && !code.isNullOrBlank()
}


