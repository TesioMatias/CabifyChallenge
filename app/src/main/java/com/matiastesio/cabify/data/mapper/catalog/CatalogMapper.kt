package com.matiastesio.cabify.data.mapper.catalog

import com.matiastesio.cabify.data.db.discount.entity.DiscountEntity
import com.matiastesio.cabify.data.db.product.entity.ProductEntity
import com.matiastesio.cabify.data.model.CatalogItemModel

interface CatalogMapper {
    fun mapResponseToModel(
        productList: List<ProductEntity>?,
        discountList: List<DiscountEntity>?
    ): List<CatalogItemModel>
}