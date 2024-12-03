package com.toDoApp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toDoApp.models.CallDataModel
import com.toDoApp.models.searchByName
import com.toDoApp.models.sortByName
import com.toDoApp.network.GetCallsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@HiltViewModel
class CallListViewModel @javax.inject.Inject constructor(
    private val getCallsUseCase: GetCallsUseCase,
) : ViewModel() {
    var query = mutableStateOf("")
    val callDataModelList = mutableStateOf<List<CallDataModel>>(emptyList())
    val searchList = mutableStateOf<List<CallDataModel>>(emptyList())
    val isLoading = mutableStateOf(false)
    val isSorted = mutableStateOf(false)

    init {
        fetchCalls()
    }

    private fun fetchCalls() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                val fetchedCalls = getCallsUseCase()
                callDataModelList.value = fetchedCalls.map { call ->
                    call.copy(isCalled = mutableStateOf(false))
                }
                searchList.value = fetchedCalls.map { call ->
                    call.copy(isCalled = mutableStateOf(false))
                }
                Log.d("aaaaaaa", "fetchCalls:${callDataModelList.value} ")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("catchaa", "fetchCalls:$e ")

            } finally {
                isLoading.value = false
            }
        }
    }

    fun onSearchValue(value: String) {
        searchList.value = searchByName(callDataModelList.value, value)
    }

    fun getSortedList() {
        isSorted.value=!isSorted.value
        if (isSorted.value) {

            searchList.value = sortByName(callDataModelList.value)
        } else {

            searchList.value = callDataModelList.value
        }
    }


}



