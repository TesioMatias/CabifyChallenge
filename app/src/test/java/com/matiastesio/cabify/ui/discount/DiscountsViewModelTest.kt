package com.matiastesio.cabify.ui.discount

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.mapper.discount.DiscountMapper
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.domain.usecases.GetDiscountsUseCase
import com.matiastesio.cabify.ui.discounts.DiscountsViewModel
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

class DiscountsViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getDiscountsUseCase: GetDiscountsUseCase

    @RelaxedMockK
    private lateinit var discountMapper: DiscountMapper

    private lateinit var discountsViewModel: DiscountsViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        discountsViewModel = DiscountsViewModel(
            getDiscountsUseCase,
            discountMapper
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
            coEvery { getDiscountsUseCase() } returns DiscountDataSource.getDiscounts
            coEvery {
                discountMapper.mapEntityListToModelList(
                    DiscountDataSource.getDiscounts
                )
            } returns DiscountDataSource.getModelList

            // When
            discountsViewModel.getDiscounts()

            // Then
            assert(discountsViewModel.discounts.value == DiscountDataSource.getModelList)
        }

    @Test
    fun `given an error then return an empty list`() =
        runTest {
            // Given
            coEvery { getDiscountsUseCase() } returns DiscountDataSource.getDiscounts
            coEvery {
                discountMapper.mapEntityListToModelList(
                    DiscountDataSource.getDiscounts
                )
            }

            // When
            discountsViewModel.getDiscounts()

            // Then
            assert(discountsViewModel.discounts.value == listOf<DiscountModel>())
        }
}
