package com.matiastesio.cabify.data.repositories.cart

import com.matiastesio.cabify.data.db.cart.CartDao
import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
) : CartRepository {
    override suspend fun getItemByCode(code: String): CartEntity? = cartDao.getCartItemById(code)

    override suspend fun insert(entity: CartEntity) = cartDao.insert(entity)

    override suspend fun update(code: String) = cartDao.updateQuantity(code)

    override suspend fun getCartList(): List<CartEntity>? = cartDao.getCart()

    override suspend fun clearCart() = cartDao.clearCart()
}
