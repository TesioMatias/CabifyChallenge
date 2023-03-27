package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.repositories.cart.CartRepository
import com.matiastesio.cabify.data.repositories.catalog.CatalogRepository
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.domain.usecases.StoreCatalogUseCase
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

class StoreCatalogUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var catalogRepository: CatalogRepository

    private lateinit var storeCatalogUseCase: StoreCatalogUseCase


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        storeCatalogUseCase = StoreCatalogUseCase(
            catalogRepository
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a model then store`() =
        runTest {
            // Given
            coEvery { catalogRepository.store(CatalogTestDataSource.getListNewItemsOne) } returns Unit

            // When
            val result = storeCatalogUseCase(CatalogTestDataSource.getListNewItemsOne)

            // Then
            assert(result)
        }

    @Test
    fun `given a model then faile at storing`() =
        runTest {
            // Given
            coEvery { catalogRepository.store(CatalogTestDataSource.getListNewItemsOne) }

            // When
            val result = storeCatalogUseCase(CatalogTestDataSource.getListNewItemsOne)

            // Then
            assert(!result)
        }
}