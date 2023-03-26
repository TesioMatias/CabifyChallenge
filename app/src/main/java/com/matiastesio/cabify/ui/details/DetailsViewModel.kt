package com.matiastesio.cabify.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.domain.usecases.AddToCartUseCase
import com.matiastesio.cabify.domain.usecases.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private var _detail = MutableLiveData<CatalogItemModel>()
    val detail: LiveData<CatalogItemModel> get() = _detail

    private var _emptyState = MutableLiveData<Boolean>()
    val emptyState: LiveData<Boolean> get() = _emptyState

    private var _addToCartState = MutableLiveData<Boolean>()
    val addToCartState: LiveData<Boolean> get() = _addToCartState

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        _emptyState.postValue(true)
    }

    fun getDetail(code: String) {
        viewModelScope.launch(handler) {
            val detailModel = getDetailUseCase(code)
            _detail.postValue(detailModel)
        }
    }

    fun addToCart(itemModel: CatalogItemModel) {
        viewModelScope.launch(handler) {
            val isSuccessful = addToCartUseCase(itemModel)
            _addToCartState.postValue(isSuccessful)
        }
    }
}