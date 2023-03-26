package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.db.cart.entity.CartEntity
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository,
    private val cartMapper: DaoCartMapper
) {
    suspend operator fun invoke(item: CatalogItemModel): Boolean =
        try {
            val itemEntity: CartEntity? = cartRepository.getItemByCode(item.code)
            if (itemEntity == null) {
                cartRepository.insert(cartMapper.toEntity(item))
            } else {
                cartRepository.update(itemEntity.code)
            }
            true
        } catch (e: Exception) {
            false
        }
}
