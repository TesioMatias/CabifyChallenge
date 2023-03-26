package com.matiastesio.cabify.data.repositories.cart

import com.matiastesio.cabify.data.db.cart.entity.CartEntity

interface CartRepository {
    suspend fun getItemByCode(code: String): CartEntity?
    suspend fun insert(entity: CartEntity)
    suspend fun update(code: String)
    suspend fun getCartList(): List<CartEntity>?
    suspend fun clearCart()
}
