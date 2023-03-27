package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.domain.usecases.GetCartUseCase
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

class GetCartUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var cartRepository: CartRepository
    @RelaxedMockK
    private lateinit var daoCartMapper: DaoCartMapper

    private lateinit var getCartUseCase: GetCartUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCartUseCase = GetCartUseCase(
            cartRepository,
            daoCartMapper
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given get cart use case then return the cart items`() =
        runTest {
            // Given
            coEvery { cartRepository.getCartList() } returns CartDataSource.getCartEntityList
            coEvery {
                daoCartMapper.fromEntity(
                    CartDataSource.getCartEntityList
                )
            } returns CartDataSource.getCartItems


            // When
            val result = getCartUseCase()

            // Then
            assert(result == CartDataSource.getCartItems)
        }
}