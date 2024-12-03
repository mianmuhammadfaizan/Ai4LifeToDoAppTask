package com.toDoApp.network

import com.toDoApp.models.CallDataModel
import com.toDoApp.models.ProductDataModel

interface CallRepository {
    suspend fun getCalls(): List<CallDataModel>
    suspend fun getProductList(): List<ProductDataModel>

}