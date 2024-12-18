package com.toDoApp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toDoApp.models.ProductDataModel
import com.toDoApp.network.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
) :
    ViewModel() {
    val productList = mutableStateOf<List<ProductDataModel>>(emptyList())
    val isLoading = mutableStateOf(false)

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                productList.value = getProductUseCase()
                Log.d("aaaaaaa", "fetchCalls:${productList.value} ")
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
    fun updateQuantity(productId: Int, increment: Boolean) {
        val updatedList = productList.value.map { product ->
            if (product.id == productId) {
                // Update the quantity
                product.copy(quantity = product.quantity + if (increment) 1 else -1)
            } else {
                product
            }
        }
        productList.value = updatedList // Reassign to trigger recomposition
    }


}