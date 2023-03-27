package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.domain.usecases.AddToCartUseCase
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.domain.usecases.GetCartUseCase
import com.matiastesio.cabify.ui.cart.CartDataSource
import com.matiastesio.cabify.ui.cart.CartViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddToCartUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var cartRepository: CartRepository

    @RelaxedMockK
    private lateinit var cartMapper: DaoCartMapper

    private lateinit var addToCartUseCase: AddToCartUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        addToCartUseCase = AddToCartUseCase(
            cartRepository,
            cartMapper
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a model then add item to cart`() =
        runTest {
            // Given
            coEvery { cartRepository.getItemByCode("code") } returns CartDataSource.getCartEntity
            coEvery { cartRepository.insert(CartDataSource.getCartEntity) } returns Unit
            coEvery { cartRepository.update("code") } returns Unit

            // When
            val result = addToCartUseCase(CartDataSource.getCartItem)

            // Then
            assert(result)
        }

    @Test
    fun `given an incorrect model then do not add item to cart`() =
        runTest {
            // Given
            coEvery { cartRepository.getItemByCode("code") } returns CartDataSource.getCartEntity
            coEvery { cartRepository.insert(CartDataSource.getCartEntity) }
            coEvery { cartRepository.update("code") }

            // When
            val result = addToCartUseCase(CartDataSource.getCartItem)

            // Then
            assert(!result)
        }
}