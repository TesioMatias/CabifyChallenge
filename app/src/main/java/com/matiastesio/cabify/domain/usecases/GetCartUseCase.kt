package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val daoCartMapper: DaoCartMapper
) {
    suspend operator fun invoke(): List<CartItemModel> {
        val response = cartRepository.getCartList()
        return daoCartMapper.fromEntity(response)
    }
}
