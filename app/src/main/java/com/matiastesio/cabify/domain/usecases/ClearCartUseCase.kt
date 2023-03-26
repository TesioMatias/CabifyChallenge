package com.matiastesio.cabify.domain.usecases

import com.matiastesio.cabify.data.repositories.cart.CartRepository
import javax.inject.Inject

class ClearCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke() = cartRepository.clearCart()
}