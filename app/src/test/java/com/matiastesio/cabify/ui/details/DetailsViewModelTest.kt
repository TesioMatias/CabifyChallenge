package com.matiastesio.cabify.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.domain.usecases.AddToCartUseCase
import com.matiastesio.cabify.domain.usecases.GetDetailUseCase
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

class DetailsViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getDetailUseCase: GetDetailUseCase

    @RelaxedMockK
    private lateinit var addToCartUseCase: AddToCartUseCase

    private lateinit var detailViewModel: DetailsViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailViewModel = DetailsViewModel(
            getDetailUseCase,
            addToCartUseCase
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
            coEvery { getDetailUseCase("code") } returns DetailDataSource.getDetail
            coEvery { addToCartUseCase(DetailDataSource.getDetail) } returns true

            // When
            detailViewModel.getDetail("code")

            // Then
            assert(detailViewModel.detail.value == DetailDataSource.getDetail)
        }

    @Test
    fun `given an error in response then return empty screen`() =
        runTest {
            // Given
            coEvery { getDetailUseCase("code") }
            coEvery { addToCartUseCase(DetailDataSource.getDetail) } returns true

            // When
            detailViewModel.getDetail("code")

            // Then
            assert(detailViewModel.emptyState.value == true)
        }

    @Test
    fun `given a correct response then add item to cart`() =
        runTest {
            // Given
            coEvery { addToCartUseCase(DetailDataSource.getDetail) } returns true

            // When
            detailViewModel.addToCart(DetailDataSource.getDetail)

            // Then
            assert(detailViewModel.addToCartState.value == true)
        }

    @Test
    fun `given an error in response then fail adding to cart`() =
        runTest {
            // Given
            coEvery { addToCartUseCase(DetailDataSource.getDetail) } returns false

            // When
            detailViewModel.addToCart(DetailDataSource.getDetail)

            // Then
            assert(detailViewModel.addToCartState.value == false)
        }
}