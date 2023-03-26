package com.matiastesio.cabify.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CheckoutViewModel : ViewModel() {

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    private var _exit = MutableLiveData<Boolean>()
    val exit: LiveData<Boolean> get() = _exit

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
        _success.postValue(true)
        _exit.postValue(true)
    }

    fun processCheckout() {
        viewModelScope.launch(handler) {
            delay(1000)
            _success.postValue(true)
            delay(1300)
            _exit.postValue(true)
        }
    }
}