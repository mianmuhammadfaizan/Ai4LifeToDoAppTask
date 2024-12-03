package com.toDoApp.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CallDataModel(
    val id: Int,
    val name: String,
    var number: String,
    var isCalled: MutableState<Boolean> = mutableStateOf(false)
)
fun searchByName(calls: List<CallDataModel>, query: String): List<CallDataModel> {
    return calls.filter { it.name.contains(query, ignoreCase = true) }
}

fun sortByName(calls: List<CallDataModel>): List<CallDataModel> {
    return calls.sortedBy { it.name }
}