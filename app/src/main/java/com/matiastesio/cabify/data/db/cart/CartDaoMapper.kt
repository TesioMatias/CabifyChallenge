package com.matiastesio.cabify.data.db.cart

import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.data.model.CatalogItemModel

class CartDaoMapper : DaoCartMapper {
    override fun toEntity(item: CatalogItemModel): CartEntity =
        CartEntity(
            code = item.code,
            name = item.name,
            price = item.price,
            icon = item.icon,
            title = item.discount?.title ?: "",
            afterQty = item.discount?.afterQty ?: -1,
            discount = item.discount?.discount ?: -1.0,
            discountType = item.discount?.discountType ?: "",
            quantity = 1
        )

    override fun fromEntity(items: List<CartEntity>?): List<CartItemModel> {
        val list = mutableListOf<CartItemModel>()
        items?.let {
            items.forEach {
                list.add(
                    CartItemModel(
                        code = it.code,
                        name = it.name,
                        price = it.price,
                        icon = it.icon,
                        title = it.title,
                        afterQty = it.afterQty,
                        discount = it.discount,
                        discountType = it.discountType,
                        quantity = it.quantity
                    )
                )
            }
        }
        return list
    }
}
