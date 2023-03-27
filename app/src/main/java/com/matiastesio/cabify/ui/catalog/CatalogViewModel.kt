package com.matiastesio.cabify.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiastesio.cabify.data.mapper.catalog.CatalogMapper
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.domain.usecases.GetDiscountsUseCase
import com.matiastesio.cabify.domain.usecases.GetProductListUseCase
import com.matiastesio.cabify.domain.usecases.StoreCatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getProductsUseCase: GetProductListUseCase,
    private val getDiscountsUseCase: GetDiscountsUseCase,
    private val storeCatalogUseCase: StoreCatalogUseCase,
    private val catalogMapper: CatalogMapper
) : ViewModel() {

    private var _catalogItems = MutableLiveData<List<CatalogItemModel>>()
    val catalogItems: LiveData<List<CatalogItemModel>> get() = _catalogItems

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        _isLoading.postValue(false)
        _catalogItems.postValue(listOf())
    }

    fun getProducts() {
        viewModelScope.launch(handler) {
            supervisorScope {
                _isLoading.postValue(true)

                val productResponse = async { getProductsUseCase() }
                val discountsResponse = async { getDiscountsUseCase() }

                val prodResponse = productResponse.await()
                val discResponse = discountsResponse.await()

                val result = catalogMapper.mapResponseToModel(
                    prodResponse,
                    discResponse
                )

                val storeState = storeCatalogUseCase(result)

                _isLoading.postValue(!storeState)
                _catalogItems.postValue(result)
            }
        }
    }
}
