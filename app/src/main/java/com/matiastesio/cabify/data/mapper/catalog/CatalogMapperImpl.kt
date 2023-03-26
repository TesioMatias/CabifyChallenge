package com.matiastesio.cabify.data.mapper.catalog

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.model.DiscountModel

class CatalogMapperImpl : CatalogMapper {

    override fun mapResponseToModel(
        productList: List<ProductEntity>?,
        discountList: List<DiscountEntity>?
    ): List<CatalogItemModel> =
        productList?.let {
            val list = mutableListOf<CatalogItemModel>()
            productList.forEach {
                list.add(mapItem(it, discountList))
            }
            list
        } ?: run {
            listOf()
        }

    private fun mapItem(
        item: ProductEntity,
        discountList: List<DiscountEntity>?
    ): CatalogItemModel {
        return CatalogItemModel(
            code = item.code,
            name = item.name,
            price = item.price,
            icon = item.icon,
            discount = getDiscount(item.code, discountList)
        )
    }

    private fun getDiscount(code: String?, discountList: List<DiscountEntity>?): DiscountModel? {
        discountList?.forEach {
            if (it.code == code) {
                return DiscountModel(
                    code = it.code,
                    discountType = it.discountType,
                    discount = it.discount,
                    afterQty = it.afterQty,
                    title = it.title
                )
            }
        }
        return null
    }
}
