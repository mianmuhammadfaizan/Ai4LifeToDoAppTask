package com.toDoApp.storage.database

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toDoApp.models.Sell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellViewModel @Inject constructor(
    private val getAllSellsUseCase: GetAllSellsUseCase,
    private val insertSellUseCase: InsertSellUseCase,
    private val updateSellUseCase: UpdateSellUseCase,
    private val deleteSellUseCase: DeleteSellUseCase
) : ViewModel() {

    var price = mutableStateOf("")
    var category = mutableStateOf("")
    var description = mutableStateOf("")
    val addState = mutableStateOf(false)
    val itemId = mutableStateOf(-1)
    private val _sells = MutableStateFlow<List<Sell>>(emptyList())
    val sells: StateFlow<List<Sell>> = _sells

    init {
        getAllSells()
    }

    private fun getAllSells() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllSellsUseCase().collect {
                _sells.value = it
            }
        }
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {
            insertSellUseCase(getObjectToInsert())
            resValue()

        }
    }


    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            updateSellUseCase(getObjectToUpdate())
            resValue()
        }
    }

    fun delete(sell: Sell) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSellUseCase(sell)
        }
    }

    fun isValidInput(): Boolean {
        return price.value.isNotEmpty() && category.value.isNotEmpty() && description.value.isNotEmpty()
    }

    fun getObjectToInsert(): Sell {
        return if (itemId.value == -1) {
            Sell(
                price = price.value.toDouble(),
                category = category.value,
                description = description.value
            )
        } else {
            Sell(
                id = itemId.value,
                price = price.value.toDouble(),
                category = category.value,
                description = description.value
            )
        }
    }

    private fun resValue() {
        price.value = ""
        category.value = ""
        description.value = ""
        addState.value = false
        itemId.value = -1
    }

    fun setDataForEdit(item: Sell) {

        itemId.value = item.id
        price.value = item.price.toString()
        category.value = item.category
        description.value = item.description


    }

    fun getObjectToUpdate(): Sell {
        return Sell(
            id = itemId.value,
            price = price.value.toDouble(),
            category = category.value,
            description = description.value
        )


    }

    fun getTotalPriceForSellItem(sellsList: List<Sell>): Double {
        return sellsList.sumOf { it.price }
    }
}


