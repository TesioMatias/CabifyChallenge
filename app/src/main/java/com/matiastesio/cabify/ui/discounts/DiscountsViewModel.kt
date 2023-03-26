package com.matiastesio.cabify.ui.discounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiastesio.cabify.data.mapper.discount.DiscountMapper
import com.matiastesio.cabify.data.model.DiscountModel
import com.matiastesio.cabify.domain.usecases.GetDiscountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscountsViewModel @Inject constructor(
    private val getDiscountsUseCase: GetDiscountsUseCase,
    private val discountMapper: DiscountMapper
) : ViewModel() {

    private var _discounts = MutableLiveData<List<DiscountModel>>()
    val discounts: LiveData<List<DiscountModel>> get() = _discounts

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        _isLoading.postValue(false)
        _discounts.postValue(mutableListOf())
    }

    fun getDiscounts() {
        viewModelScope.launch(handler) {
            _isLoading.postValue(true)

            val discountsResponse = getDiscountsUseCase()
            val result = discountMapper.mapEntityListToModelList(discountsResponse)

            _isLoading.postValue(false)
            _discounts.postValue(result)
        }
    }
}
