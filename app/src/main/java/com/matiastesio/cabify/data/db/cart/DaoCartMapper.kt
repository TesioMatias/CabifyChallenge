package com.matiastesio.cabify.data.db.cart

import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.data.model.CatalogItemModel

interface DaoCartMapper {
    fun toEntity(item: CatalogItemModel): CartEntity
    fun fromEntity(item: List<CartEntity>?): List<CartItemModel>
}
