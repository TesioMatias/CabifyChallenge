package com.matiastesio.cabify.ui.catalog

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matiastesio.cabify.data.mapper.catalog.CatalogMapper
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.domain.usecases.GetDiscountsUseCase
import com.matiastesio.cabify.domain.usecases.GetProductListUseCase
import com.matiastesio.cabify.domain.usecases.StoreCatalogUseCase
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

class CatalogViewModelTest {

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getProductListUseCase: GetProductListUseCase

    @RelaxedMockK
    private lateinit var getDiscountsUseCase: GetDiscountsUseCase

    @RelaxedMockK
    private lateinit var storeCatalogUseCase: StoreCatalogUseCase

    @RelaxedMockK
    private lateinit var catalogMapper: CatalogMapper

    private lateinit var catalogViewModel: CatalogViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        catalogViewModel = CatalogViewModel(
            getProductListUseCase,
            getDiscountsUseCase,
            storeCatalogUseCase,
            catalogMapper,
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given a correct response in each use case then return the items to show on screen`() =
        runTest {
            // Given
            coEvery { getProductListUseCase() } returns CatalogTestDataSource.getProductList
            coEvery { getDiscountsUseCase() } returns CatalogTestDataSource.getDiscountList
            coEvery {
                catalogMapper.mapResponseToModel(
                    CatalogTestDataSource.getProductList,
                    CatalogTestDataSource.getDiscountList
                )
            } returns CatalogTestDataSource.getCatalogList
            coEvery { storeCatalogUseCase(CatalogTestDataSource.getCatalogList) } returns true

            // When
            catalogViewModel.getProducts()

            // Then
            assert(catalogViewModel.catalogItems.value == CatalogTestDataSource.getCatalogList)
        }

    @Test
    fun `given an error then return an empty list`() =
        runTest {
            // Given
            coEvery { getProductListUseCase() } returns CatalogTestDataSource.getProductList
            coEvery { getDiscountsUseCase() } returns CatalogTestDataSource.getDiscountList
            coEvery {
                catalogMapper.mapResponseToModel(
                    CatalogTestDataSource.getProductList,
                    CatalogTestDataSource.getDiscountList
                )
            } returns CatalogTestDataSource.getCatalogList
            coEvery { storeCatalogUseCase(CatalogTestDataSource.getCatalogList) }

            // When
            catalogViewModel.getProducts()

            // Then
            assert(catalogViewModel.catalogItems.value == listOf<CatalogItemModel>())
        }
}