package com.toDoApp.viewmodels

import com.toDoApp.models.CallDataModel
import com.toDoApp.models.ProductDataModel
import com.toDoApp.network.ApiService
import jakarta.inject.Inject


interface CallRepository {
    suspend fun getCalls(): List<CallDataModel>
    suspend fun getProductList(): List<ProductDataModel>

}

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


class GetCallsUseCase @Inject constructor(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(): List<CallDataModel> {
        return callRepository.getCalls()
    }
}

class GetProductUseCase @Inject constructor(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(): List<ProductDataModel> {
        return callRepository.getProductList()
    }
}
