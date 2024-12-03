package com.toDoApp.models


data class ProductDataModel(
    val id: Int,
    val name: String,
    val price: Int,
    var quantity: Int,
    val type: Int
)
