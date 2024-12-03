package com.toDoApp.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CallDataModel(
    val id: Int,
    val name: String,
    var number: String,
    var isCalled: MutableState<Boolean> = mutableStateOf(false)
)