package com.mobilestudio.mypetshop.detailproduct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobilestudio.repository.ProductsRepository
import kotlinx.coroutines.launch

class DetailProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProductsRepository(application)

    private val mutableQuantity = MutableLiveData<Int>()
    val quantity: LiveData<Int>
        get() = mutableQuantity

    init {
        mutableQuantity.value = 1
    }

    fun addQuantity() {
        // ++
        mutableQuantity.value = mutableQuantity.value?.plus(1)
    }

    fun minusQuantity() {
        // --
        val helperQuantity = mutableQuantity.value ?: 0
        if (helperQuantity > 0) {
            mutableQuantity.value = mutableQuantity.value?.minus(1)
        }
    }

    fun addProductToCart(nombre: String, precio: String) {
        viewModelScope.launch {
            repository.addProductToCart(nombre, precio, quantity.value ?: 1)
        }
    }

}