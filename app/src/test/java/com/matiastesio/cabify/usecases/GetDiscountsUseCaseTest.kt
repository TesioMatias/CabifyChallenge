package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.discount.DaoDiscountMapper
import com.matiastesio.cabify.data.repositories.discount.DiscountRepository
import com.matiastesio.cabify.domain.usecases.GetDiscountsUseCase
import com.matiastesio.cabify.ui.discount.DiscountDataSource
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

class GetDiscountsUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var discountRepository: DiscountRepository

    @RelaxedMockK
    private lateinit var daoMapper: DaoDiscountMapper

    private lateinit var getDiscountsUseCase: GetDiscountsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDiscountsUseCase = GetDiscountsUseCase(
            discountRepository,
            daoMapper
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given get discounts use case then return the discounts`() =
        runTest {
            // Given
            coEvery { discountRepository.getDiscountsFromRemote() } returns DiscountDataSource.getDiscountResponse
            coEvery { discountRepository.getDiscountsFromLocal() } returns DiscountDataSource.getDiscounts
            coEvery { discountRepository.clearDiscounts() } returns Unit
            coEvery { discountRepository.insertDiscounts(DiscountDataSource.getDiscounts) } returns Unit
            coEvery {
                daoMapper.mapToDao(DiscountDataSource.getResponseList)
            } returns DiscountDataSource.getDiscounts


            // When
            val result = getDiscountsUseCase()

            // Then
            assert(result == DiscountDataSource.getDiscounts)
        }
}