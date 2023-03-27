package com.matiastesio.cabify.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiastesio.cabify.data.mapper.discount.DiscountMapper
import com.matiastesio.cabify.data.model.CartItemModel
import com.matiastesio.cabify.domain.usecases.ClearCartUseCase
import com.matiastesio.cabify.domain.usecases.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val clearCartUserCase: ClearCartUseCase
) : ViewModel() {

    private var _cartItems = MutableLiveData<List<CartItemModel>?>()
    val cartItems: LiveData<List<CartItemModel>?> get() = _cartItems

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private var _doReturnToCatalog = MutableLiveData<Boolean>()
    val doReturnToCatalog: LiveData<Boolean> get() = _doReturnToCatalog

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        _isLoading.postValue(false)
        _cartItems.postValue(mutableListOf())
    }

    fun getCart() {
        viewModelScope.launch(handler) {
            _isLoading.postValue(true)

            val cartItems = getCartUseCase()

            _isLoading.postValue(false)
            _cartItems.postValue(cartItems)
        }
    }

    fun clearCart() {
        viewModelScope.launch(handler) {
            val result = clearCartUserCase()
            _doReturnToCatalog.postValue(result)
        }
    }

    fun payCart() {
        viewModelScope.launch(handler) {
            clearCartUserCase()
        }
    }
}
