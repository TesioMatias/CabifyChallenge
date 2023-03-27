package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.domain.usecases.AddToCartUseCase
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.ui.cart.CartDataSource
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

class ClearCartUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var cartRepository: CartRepository

    private lateinit var clearCartUseCase: ClearCartUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        clearCartUseCase = ClearCartUseCase(
            cartRepository
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a model then clear cart`() =
        runTest {
            // Given
            coEvery { cartRepository.clearCart() } returns Unit

            // When
            val result = clearCartUseCase()

            // Then
            assert(result)
        }

    @Test
    fun `given an incorrect model then fail clearing cart`() =
        runTest {
            // Given
            coEvery { cartRepository.clearCart() }

            // When
            val result = clearCartUseCase()

            // Then
            assert(!result)
        }
}