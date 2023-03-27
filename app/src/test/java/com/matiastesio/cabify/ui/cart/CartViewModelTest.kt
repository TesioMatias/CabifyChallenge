package com.matiastesio.cabify.ui.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.domain.usecases.GetCartUseCase
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

class CartViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getCartUseCase: GetCartUseCase

    @RelaxedMockK
    private lateinit var clearCartUseCase: ClearCartUseCase

    private lateinit var cartViewModel: CartViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cartViewModel = CartViewModel(
            getCartUseCase,
            clearCartUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a correct response then return the item to show on screen`() =
        runTest {
            // Given
            coEvery { getCartUseCase() } returns CartDataSource.getCartItems
            coEvery { clearCartUseCase() } returns Unit

            // When
            cartViewModel.getCart()

            // Then
            assert(cartViewModel.cartItems.value == CartDataSource.getCartItems)
        }

    @Test
    fun `given an error in use cases then return an empty list`() =
        runTest {
            // Given
            coEvery { getCartUseCase() }
            coEvery { clearCartUseCase() }

            // When
            cartViewModel.getCart()

            // Then
            assert(cartViewModel.cartItems.value == listOf<CartItemModel>())
        }

    @Test
    fun `given a correct clear cart use case then go to catalog`() =
        runTest {
            // Given
            coEvery { clearCartUseCase() } returns Unit

            // When
            cartViewModel.clearCart()

            // Then
            assert(cartViewModel.doReturnToCatalog.value == true)
        }
}
