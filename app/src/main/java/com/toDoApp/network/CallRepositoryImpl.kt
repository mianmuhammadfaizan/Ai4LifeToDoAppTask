package com.toDoApp.network

import com.toDoApp.models.CallDataModel
import com.toDoApp.models.ProductDataModel
import jakarta.inject.Inject


class CallRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CallRepository {
    override suspend fun getCalls(): List<CallDataModel> {
        return apiService.getCalls()
    }

    override suspend fun getProductList(): List<ProductDataModel> {
        return apiService.getProduct()
    }
}