package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.cart.DaoCartMapper
import com.matiastesio.cabify.data.db.catalog.DaoCatalogMapper
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepository
import com.matiastesio.cabify.domain.usecases.GetCartUseCase
import com.matiastesio.cabify.domain.usecases.GetDetailUseCase
import com.matiastesio.cabify.ui.cart.CartDataSource
import com.matiastesio.cabify.ui.catalog.CatalogTestDataSource
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

class GetDetailUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var catalogRepository: CatalogRepository
    @RelaxedMockK
    private lateinit var daoCatalogMapper: DaoCatalogMapper

    private lateinit var getDetailUseCase: GetDetailUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getDetailUseCase = GetDetailUseCase(
            catalogRepository,
            daoCatalogMapper
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a code then return the detail of an item`() =
        runTest {
            // Given
            coEvery { catalogRepository.getCatalogEntity("code") } returns CatalogTestDataSource.getEntity
            coEvery {
                daoCatalogMapper.fromEntity(
                    CatalogTestDataSource.getEntity
                )
            } returns CatalogTestDataSource.getModel


            // When
            val result = getDetailUseCase("code")

            // Then
            assert(result == CatalogTestDataSource.getModel)
        }
}