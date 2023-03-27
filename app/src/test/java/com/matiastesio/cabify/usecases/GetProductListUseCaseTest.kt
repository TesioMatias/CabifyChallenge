package com.matiastesio.cabify.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.db.product.DaoProductMapper
import com.matiastesio.cabify.data.repositories.product.ProductRepository
import com.matiastesio.cabify.domain.usecases.GetProductListUseCase
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

class GetProductListUseCaseTest {
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var productRepository: ProductRepository

    @RelaxedMockK
    private lateinit var daoMapper: DaoProductMapper

    private lateinit var getProductListUseCase: GetProductListUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getProductListUseCase = GetProductListUseCase(
            productRepository,
            daoMapper
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given get products use case then return the products mapped`() =
        runTest {
            // Given
            coEvery { productRepository.getProductsFromRemote() } returns CatalogTestDataSource.getResponse
            coEvery { productRepository.getProductsFromLocal() } returns CatalogTestDataSource.getProductList
            coEvery { productRepository.clearProducts() } returns Unit
            coEvery { productRepository.insertProducts(CatalogTestDataSource.getProductList) } returns Unit
            coEvery {
                daoMapper.mapToDao(CatalogTestDataSource.getResponse.products)
            } returns CatalogTestDataSource.getProductList


            // When
            val result = getProductListUseCase()

            // Then
            assert(result == CatalogTestDataSource.getProductList)
        }
}
